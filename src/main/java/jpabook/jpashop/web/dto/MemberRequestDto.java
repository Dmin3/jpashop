package jpabook.jpashop.web.dto;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    private String password;
    private String city;
    private String street;
    private String zip;

    @Builder
    public MemberRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .address(new Address(city, street, zip))
                .build();
    }



}
