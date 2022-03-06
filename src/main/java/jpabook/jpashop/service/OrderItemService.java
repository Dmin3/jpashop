package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.web.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final int count = 1;

    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //장바구니 담기
    public void save(Long id, Long memberId){

        Member member = memberRepository.find(memberId);
        Item item = itemRepository.find(id);

        OrderItem orderItem = OrderItem.createOrderItem(member, item, item.getPrice(), count);

        orderItemRepository.save(orderItem);

    }






}
