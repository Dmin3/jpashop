package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemFindAllResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String company;


}
