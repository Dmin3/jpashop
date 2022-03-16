package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.web.dto.ItemDto;
import lombok.*;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String company;

    //==비즈니스 로직==//

    //재고 수량 증가
    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 수량 감소
    public void removeStock(int quantity) {
        int nmg = this.stockQuantity - quantity;
        if (nmg < 0) {
            throw new NotEnoughStockException("재고가 충분하지 않습니다.");
        }
        this.stockQuantity = nmg;
    }

}
