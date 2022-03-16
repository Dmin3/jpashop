package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // enumType은 꼭 String!!
    private DeliveryStatus status;

    @Builder
    private Delivery(Order order, Address address, DeliveryStatus status) {
        this.order = order;
        this.address = address;
        this.status = status;
    }
}
