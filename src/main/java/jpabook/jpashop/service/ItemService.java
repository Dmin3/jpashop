package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Phone;
import jpabook.jpashop.domain.item.Tv;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.web.dto.ItemDto;
import jpabook.jpashop.web.dto.ItemSaveRequestDto;
import jpabook.jpashop.web.dto.MemberItemAllFindResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
               return Tv.builder()
                       .name(requestDto.getName())
                       .company(requestDto.getCompany())
                       .price(requestDto.getPrice())
                       .stockQuantity(requestDto.getStockQuantity()).build();
            case "C":
                return Computer.builder()
                                .name(requestDto.getName())
                            .company(requestDto.getCompany())
                            .price(requestDto.getPrice())
                            .stockQuantity(requestDto.getStockQuantity()).build();

            case "P":
                return Phone.builder()
                        .name(requestDto.getName())
                        .company(requestDto.getCompany())
                        .price(requestDto.getPrice())
                        .stockQuantity(requestDto.getStockQuantity()).build();

        }
        return null;
    }

    public List<ItemDto> findItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> collect = items.stream().map(i -> new ItemDto(i.getId(),i.getName(), i.getPrice(), i.getStockQuantity(), i.getCompany())).collect(Collectors.toList());
        return collect;
    }

    public List<MemberItemAllFindResponseDto> findMemberItems() {
        List<Item> items = itemRepository.findAll();
        List<MemberItemAllFindResponseDto> collect = items.stream().map(i -> new MemberItemAllFindResponseDto(i.getId(), i.getName(), i.getPrice(), i.getCompany())).collect(Collectors.toList());
        return collect;
    }

    public Item findOne(Long itemId) {
        return itemRepository.find(itemId);
    }

    @Transactional
    public ItemDto update(Long id, ItemDto itemDto) {

        Item item = itemRepository.find(id);
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setCompany(itemDto.getCompany());
        item.setStockQuantity(itemDto.getStockQuantity());

        return new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), item.getCompany());

    }

    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.find(id);
        itemRepository.remove(item);
    }
}
