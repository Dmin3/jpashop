package jpabook.jpashop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    @GetMapping("/itemSave")
    public String itemSave() {
        return "/item/itemSave";
    }


}