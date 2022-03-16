package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.web.dto.MemberRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                                                        .name("member1")
                                                        .password("123")
                                                        .build();
        //when
        memberService.join(requestDto);

    }

//    @Test
//    public void 중복_회원_예외() throws Exception {
//        //given
//        Address address = new Address("11", "11", "12");
//        Member member = Member.builder().name("member1").address(address).orders(null).build();
//        Member member1 = Member.builder().name("member1").address(address).orders(null).build();
//        //when
//        memberService.join(member1);
//
//
//        //예외가 발생해야된다.
//        //then
//        assertThrows(IllegalStateException.class, () -> memberService.join(member1));
//
//    }




}