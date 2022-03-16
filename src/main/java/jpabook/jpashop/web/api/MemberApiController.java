package jpabook.jpashop.web.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderItemService;
import jpabook.jpashop.service.OrderService;
import jpabook.jpashop.web.dto.MemberRequestDto;
import jpabook.jpashop.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final OrderItemService orderItemService;

    //회원가입
    @PostMapping("/api/v1/member-save")
    public MemberResponseDto save(@RequestBody MemberRequestDto requestDto) {
        Long id = memberService.join(requestDto);
        Member member = memberRepository.find(id);
        return new MemberResponseDto(member.getId(), member.getName(), member.getAddress());
    }

    //로그인
    @PostMapping("/api/v1/member-login")
    public MemberResponseDto login(@RequestBody MemberRequestDto requestDto, HttpSession session) {

        if (memberService.login(requestDto)){
            Member member = memberRepository.findOneByName(requestDto.getName());
            session.setAttribute("member", member);
        }
        Member member = (Member) session.getAttribute("member");

        return new MemberResponseDto(member.getId(), member.getName(), member.getAddress());
    }



}
