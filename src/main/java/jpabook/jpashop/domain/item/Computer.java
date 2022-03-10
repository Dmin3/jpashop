package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("C")
@AllArgsConstructor
public class Computer extends Item {

    @Builder
    public Computer(String name, int price, int stockQuantity,String company) {
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setCompany(company);
    }
}
