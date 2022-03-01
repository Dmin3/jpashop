package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberItemAllFindResponseDto {
    private Long id;
    private String name;
    private int price;
    private String etc;
    private String company;
}
