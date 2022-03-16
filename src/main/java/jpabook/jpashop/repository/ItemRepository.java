package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 등록
    public void save(Item item) {
        em.persist(item);
    }

    //상품조회
    public Item find(Long itemId) {
        return em.find(Item.class, itemId);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }


    //상품 삭제
    public void remove(Item item) {
        em.remove(item);
    }
}
