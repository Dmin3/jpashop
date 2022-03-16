package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTImeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // 내가 연관관계 주인이 아니야!
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name,String password, Address address, List<Order> orders) {
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.password = password;
    }


}
