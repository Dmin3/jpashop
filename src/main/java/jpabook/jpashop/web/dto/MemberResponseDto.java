package jpabook.jpashop.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    public MemberResponseDto(Long id) {
        this.id = id;
    }
}
