package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1() {

            Member admin = Member.builder().name("admin").password("1234").build();
            em.persist(admin);

            Item item = Computer.builder().name("컴퓨터").price(5000).stockQuantity(10).company("삼성").build();
            em.persist(item);

            Item apple = Computer.builder().name("애플컴퓨터").price(50000).stockQuantity(10).company("애플").build();
            em.persist(apple);

            //주문 클릭 시
//            OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), 3);
//            em.persist(orderItem);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(apple, apple.getPrice(), 2);
//            em.persist(orderItem1);
        }

        public void dbInit2() {




        }

    }
}
