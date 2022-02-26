package jpabook.jpashop.web.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/save")
    public ResponseEntity save(@RequestBody MemberSaveRequestDto requestDto) {
        memberService.join(requestDto);
        return new ResponseEntity<>("회원가입 성공했습니다.",HttpStatus.OK);
    }


}
