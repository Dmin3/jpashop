package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("T")
@AllArgsConstructor
public class Tv extends Item{

    @Builder
    public Tv(String name, int price, int stockQuantity,String company) {
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setCompany(company);
    }

}
