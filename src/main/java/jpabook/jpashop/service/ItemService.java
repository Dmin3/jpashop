package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Phone;
import jpabook.jpashop.domain.item.Tv;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.web.dto.ItemSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(ItemSaveRequestDto requestDto){

        Item item = categoryCheck(requestDto);

        item.setName(requestDto.getName());
        item.setPrice(requestDto.getPrice());
        item.setStockQuantity(requestDto.getStockQuantity());

        itemRepository.save(item);
        return item.getId();
    }

    private Item categoryCheck(ItemSaveRequestDto requestDto) {

        switch (requestDto.getDtype()){
            case "T":
               return new Tv(requestDto.getCompany(), requestDto.getEtc());
            case "C":
                return new Computer(requestDto.getCompany(), requestDto.getEtc());
            case "P":
                return new Phone(requestDto.getCompany(), requestDto.getEtc());

        }
        return null;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.find(itemId);
    }
}
