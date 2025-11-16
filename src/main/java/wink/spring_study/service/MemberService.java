package wink.spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wink.spring_study.domain.Member;
import wink.spring_study.repository.MemberRepository;
import wink.spring_study.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// @Service
public class MemberService {
    private final MemberRepository memberRepository;
    // @Autowired 생성자에 @Autowired를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입. 생성자가 1개만 있으면 생략 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증, 같은 이름 있으면 안된당
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
