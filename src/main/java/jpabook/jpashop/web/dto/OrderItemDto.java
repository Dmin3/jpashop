package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String name;
    private String company;
    private int price;


}
