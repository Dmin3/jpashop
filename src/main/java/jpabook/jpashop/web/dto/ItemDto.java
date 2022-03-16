package jpabook.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // @Data는 생성자를 가지고 있지 않기 때문에 생성자를 만들어줘야된다.
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String company;


}
