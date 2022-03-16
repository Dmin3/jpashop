package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository {

    private final EntityManager em;

    public Long save(OrderItem orderItem){
        em.persist(orderItem);
        return orderItem.getId();
    }

    public OrderItem find(Long orderItemId) {
        return em.find(OrderItem.class, orderItemId);
    }

    public List<OrderItem> findAll() {
        return em.createQuery("select oi from OrderItem oi join fetch oi.item", OrderItem.class).getResultList();
    }


}
