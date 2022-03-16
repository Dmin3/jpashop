package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderItemRepositoryTest {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Rollback(value = false)
    public void 장바구니() throws Exception {
        //given
        Member member = Member.builder().name("userA").address(new Address("11", "22", "33")).build();
        Item item = new Computer();
        item.setCompany("삼성");
        item.setPrice(1000);
        item.setStockQuantity(10);
        item.setName("JPA BOOK");

        memberRepository.save(member);
        itemRepository.save(item);
        //when
        Member findMember = memberRepository.find(member.getId());
        Item findItem = itemRepository.find(item.getId());

        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), 2);
        orderItemRepository.save(orderItem);

        OrderItem findOrderItem = orderItemRepository.find(orderItem.getId());

        //then
        assertEquals(2, orderItem.getCount());
        assertEquals(item.getPrice(), orderItem.getOrderPrice());

    }
}