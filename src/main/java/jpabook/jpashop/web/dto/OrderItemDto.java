package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String name;
    private int price;
    private int count;
    private String company;


}
