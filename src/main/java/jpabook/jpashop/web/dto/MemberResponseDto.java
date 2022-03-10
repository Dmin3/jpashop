package jpabook.jpashop.web.dto;

import jpabook.jpashop.domain.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class MemberResponseDto {

    private Long id;
    private String name;
    private Address address;

    public MemberResponseDto(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
