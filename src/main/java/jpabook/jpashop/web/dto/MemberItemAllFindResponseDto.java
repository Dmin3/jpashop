package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberItemAllFindResponseDto {
    private Long id;
    private String name;
    private int price;
    private String company;
}
