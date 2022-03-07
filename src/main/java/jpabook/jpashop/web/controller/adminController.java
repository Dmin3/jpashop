package jpabook.jpashop.web.controller;

import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.dto.ItemFindAllResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class adminController {

    private final ItemService itemService;


    @GetMapping("/itemSave")
    public String itemSave() {
        return "/item/itemSave";
    }

    //상품전체조회
    @GetMapping("/item")
    public String findAllItem(Model model) {

        List<ItemFindAllResponseDto> items = itemService.findItems();

        model.addAttribute("item", items);

        return "/admin/item";
    }


}
