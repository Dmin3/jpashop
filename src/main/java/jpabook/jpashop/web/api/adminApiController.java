package jpabook.jpashop.web.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderItemService;
import jpabook.jpashop.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class adminApiController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final OrderItemService orderItemService;

    //admin 로그인
    @PostMapping("/v1/admin-login")
    public MemberResponseDto login(@RequestBody MemberRequestDto requestDto, HttpSession session){
        if (memberService.login(requestDto)){
            Member admin = memberRepository.findOneByName(requestDto.getName());
            session.setAttribute("admin", admin);
        } else {
            throw new IllegalStateException("존재하지 않는 회원입니다");
        }
        Member member = (Member) session.getAttribute("admin");

        return new MemberResponseDto(member.getId(), member.getName(), member.getAddress());
    }

    //상품 등록
    @PostMapping("/v1/item-save")
    public Long save(@RequestBody ItemSaveRequestDto requestDto){

        return itemService.saveItem(requestDto);
    }

    //상품 전체조회
    @GetMapping("/v1/item-findAll")
    public List<ItemDto> itemFindAll() {
       return itemService.findItems();
    }

    //상품 수정
    @PostMapping("/v1/item-update/{id}")
    public ItemDto itemUpdate(@PathVariable("id") Long id, @RequestBody ItemDto itemDto){

        return itemService.update(id, itemDto);

    }

    //상품 삭제
    @DeleteMapping("/v1/item-remove/{id}")
    public Long itemRemove(@PathVariable("id") Long id){

        itemService.delete(id);
        return id;
    }


    //주문상품 조회
    @GetMapping("/v1/orderItem-findAll")
    public List<OrderItemDto> orderItemFindAll() {
        List<OrderItemDto> all = orderItemService.findAll();
        return all;
    }

    //장바구니 담기
    @PostMapping("/v1/orderItem-save")
    public Long orderItemSave(@RequestBody OrderItemSaveDto dto) {

        return orderItemService.save(dto.getId(), dto.getCount());
    }







}
