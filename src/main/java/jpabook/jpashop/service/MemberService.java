package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(MemberRequestDto requestDto){
        //중복 회원 검증
        validateDuplicdateMember(requestDto);
        Member member = requestDto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicdateMember(MemberRequestDto requestDto) {
        //예외
        List<Member> findMembers = memberRepository.findByName(requestDto.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //한건조회
    public Member findOne(Long memberId) {
        return memberRepository.find(memberId);
    }

    //로그인 검증
    public boolean login(MemberRequestDto requestDto) {

        Member member = memberRepository.findOneByName(requestDto.getName());

        if (member == null) {
            return false;
        }
        if (!(member.getPassword().equals(requestDto.getPassword()))) {
            return false;
        }

        return true;


    }
}
