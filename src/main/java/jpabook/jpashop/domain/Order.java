package jpabook.jpashop.domain;

import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //===========================================================//

    @Builder
    private Order(Member member, Delivery delivery, OrderStatus status,LocalDateTime orderDate , List<OrderItem> orderItems) {
        this.member = member;
        this.delivery = delivery;
        this.status = status;
        this.orderDate = orderDate;
        this.orderItems = orderItems;

        //==연관관계 메소드==// 핵심적으로 컨트롤 하는 부분에 만들기(양방향 관계 일때)
        Delivery.builder().order(this).build();
//        OrderItem.builder().order(this).build();
    }

    //==연관관계 메소드==//
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    //==생성 메소드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;

    }

    /**비즈니스 로직**/

    //주문 취소
    public void cancel(){

        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 주문 취소가 불가능합니다.");
        }

        status = OrderStatus.CANCEL;

        //주문 상품 원복
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회로직==//
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }









}
