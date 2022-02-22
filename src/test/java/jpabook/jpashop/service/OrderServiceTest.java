package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Address address = new Address("11", "22", "33");
        Member member = Member.builder()
                .name("member1")
                .address(address).build();
        em.persist(member);

        Computer computer = new Computer();
        computer.setName("com1");
        computer.setCompany("intel");
        computer.setPrice(10000);
        computer.setStockQuantity(10);
        em.persist(computer);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), computer.getId(), orderCount);

        //then
        Order getOrder = orderRepository.find(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 주문 * 수량 이다");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수는 정확해야한다.");
        assertEquals(8, computer.getStockQuantity(), "재고 수량은 같아야한다");


    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Address address = new Address("11", "22", "33");
        Member member = Member.builder()
                .name("member3")
                .address(address).build();
        em.persist(member);

        Computer computer = new Computer();
        computer.setName("com3");
        computer.setCompany("intel");
        computer.setPrice(10000);
        computer.setStockQuantity(10);
        em.persist(computer);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), computer.getId(), orderCount);
        //when
        orderService.cancel(orderId);

        //then
        Order getOrder = orderRepository.find(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 상태가 CANCEL이 되어야 한다.");
        assertEquals(10, computer.getStockQuantity(), "재고 수량이 원복해야된다.");
    }

    @Test
    public void 상품주문_재고초과() throws Exception {

        //given
        Address address = new Address("11", "22", "33");
        Member member = Member.builder()
                .name("member2")
                .address(address).build();
        em.persist(member);

        Computer computer = new Computer();
        computer.setName("com2");
        computer.setCompany("intel");
        computer.setPrice(10000);
        computer.setStockQuantity(10);
        em.persist(computer);
        //when
        int orderCount = 11;

        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), computer.getId(), orderCount));

    }



}