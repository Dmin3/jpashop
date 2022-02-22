package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 내가 연관관계 주인이야!
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, Address address, List<Order> orders) {
        this.name = name;
        this.address = address;
        this.orders = orders;
    }


}
