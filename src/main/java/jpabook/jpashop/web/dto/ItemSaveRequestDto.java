package jpabook.jpashop.web.dto;

import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Tv;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSaveRequestDto {

    private String name;
    private int price;
    private int stockQuantity;
    private String company;
    private String dtype;







}
