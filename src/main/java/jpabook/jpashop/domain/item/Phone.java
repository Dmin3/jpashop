package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("P")
@AllArgsConstructor
public class Phone extends Item{

    private String company;
    private String etc;

    public Phone() {

    }
}
