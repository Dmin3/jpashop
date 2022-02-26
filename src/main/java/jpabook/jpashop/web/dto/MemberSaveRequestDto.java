package jpabook.jpashop.web.dto;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String name;
    private String password;
    private String city;
    private String street;
    private String zip;
//    private Address address;

    @Builder
    public MemberSaveRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Member toEntity() {
//        this.address = new Address(this.city, this.street, this.zip);
        return Member.builder()
                .name(name)
                .password(password)
                .address(new Address(city, street, zip))
                .build();
    }



}
