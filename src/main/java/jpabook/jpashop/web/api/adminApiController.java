package jpabook.jpashop.web.api;

import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.dto.ItemSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class adminApiController {

    private final ItemService itemService;

    //상품 등록
    @PostMapping("/api/v1/itemSave")
    public Long save(@RequestBody ItemSaveRequestDto requestDto){

        return itemService.saveItem(requestDto);


    }



}
