package jpabook.jpashop.web.controller;

import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.OrderItemService;
import jpabook.jpashop.web.dto.MemberItemAllFindResponseDto;
import jpabook.jpashop.web.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final ItemService itemService;
    private final OrderItemService orderItemService;


    @GetMapping("/main")
    public String hello(Model model) {

        List<MemberItemAllFindResponseDto> items = itemService.findMemberItems();
        model.addAttribute("item", items);

        List<OrderItemDto> orderItems = orderItemService.findAll();
        model.addAttribute("orderItem", orderItems);


        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/create")
    public String create() {
        return "/member/create";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "/member/myPage";
    }

    //상품 보기
    @GetMapping("/detailProduct")
    public String detailProduct(Model model) {



        return "/item/detailProduct";
    }

}
