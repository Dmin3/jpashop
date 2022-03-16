package jpabook.jpashop.web.controller;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        List<ItemDto> items = itemService.findItems();

        model.addAttribute("item", items);

        return "/admin/item";
    }

    //개별상품조회
    @GetMapping("/itemUpdate/{id}")
    public String findItem(@PathVariable("id") Long id, Model model){

        Item item = itemService.findOne(id);
        model.addAttribute("item", item);

        return "item/detailProduct";
    }


}
