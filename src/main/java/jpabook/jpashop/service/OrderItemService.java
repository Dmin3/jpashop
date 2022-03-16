package jpabook.jpashop.service;


import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderItemRepository;
import jpabook.jpashop.web.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {



    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;



    public List<OrderItemDto> findAll() {
        List<OrderItem> orderItem = orderItemRepository.findAll();

        List<OrderItemDto> collect = orderItem.stream().map(oi -> new OrderItemDto(oi.getId(), oi.getItem().getName(), oi.getOrderPrice(), oi.getCount(), oi.getItem().getCompany())).collect(Collectors.toList());
        return collect;
    }


    @Transactional
    public Long save(Long id, int count) {
        Item item = itemRepository.find(id);
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        return orderItemRepository.save(orderItem);
    }
}
