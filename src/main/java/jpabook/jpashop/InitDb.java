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

//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1() {

            Member member1 = Member.builder()
                    .name("userA")
                    .address(new Address("서울", "1", "12"))
                    .build();
            em.persist(member1);

            Computer computer1 = new Computer("삼성", "11");
            computer1.setName("JPA Computer1");
            computer1.setPrice(1000);
            computer1.setStockQuantity(100);
            em.persist(computer1);

            Computer computer2 = new Computer("애플", "11");
            computer2.setName("JPA Computer2");
            computer2.setPrice(5000);
            computer2.setStockQuantity(50);
            em.persist(computer2);

            OrderItem orderItem1 = OrderItem.createOrderItem(computer1, computer1.getPrice(), 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(computer2, computer2.getPrice(), 2);

            Delivery delivery = Delivery.builder()
                    .address(member1.getAddress())
                    .build();

            Order order = Order.createOrder(member1, delivery, orderItem1, orderItem2);
            em.persist(order);


        }

        public void dbInit2() {

            Member member2 = Member.builder()
                    .name("userB")
                    .address(new Address("부산", "2", "23"))
                    .build();
            em.persist(member2);

            Computer computer1 = new Computer("삼성", "11");
            computer1.setName("Spring Computer1");
            computer1.setPrice(50000);
            computer1.setStockQuantity(100);
            em.persist(computer1);

            Computer computer2 = new Computer("애플", "11");
            computer2.setName("Spring Computer2");
            computer2.setPrice(10000);
            computer2.setStockQuantity(20);
            em.persist(computer2);

            OrderItem orderItem1 = OrderItem.createOrderItem(computer1, computer1.getPrice(), 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(computer2, computer2.getPrice(), 2);

            Delivery delivery = Delivery.builder()
                    .address(member2.getAddress())
                    .build();

            Order order = Order.createOrder(member2, delivery, orderItem1, orderItem2);
            em.persist(order);


        }

    }
}
