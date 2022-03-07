package jpabook.jpashop.web.api;

import jpabook.jpashop.domain.item.Computer;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.dto.ItemFindAllResponseDto;
import jpabook.jpashop.web.dto.ItemSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class adminApiController {

    private final ItemService itemService;

    //상품 등록
    @PostMapping("/api/v1/item-save")
    public Long save(@RequestBody ItemSaveRequestDto requestDto){

        return itemService.saveItem(requestDto);


    }

    //상품 전체조회
    @GetMapping("/api/v1/item-findAll")
    public List<ItemFindAllResponseDto> ItemFindAll() {
       return itemService.findItems();
    }





}
