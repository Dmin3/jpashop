package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //조회
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.find(itemId);

        //주문상문
        Delivery delivery = Delivery.builder().address(member.getAddress()).build();
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    //취소
    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.find(orderId);
        order.cancel();
    }



}
