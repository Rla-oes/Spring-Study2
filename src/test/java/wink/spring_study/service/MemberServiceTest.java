package wink.spring_study.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import wink.spring_study.domain.Member;
import wink.spring_study.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository; //클리어 해줘야하기때문에 가져옴

    @BeforeEach //각 테스트 실행 전에 호출! 테스트가 서로 영향이 없도록 항상 새로운 객체 생성하고 의존관계도 새로 맺어 주는 것
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    public void 회원가입() throws Exception { //테스트는 과감하게 한글로 해도 ㄱㅊ. 처음 할 땐 주석 given when then 깔고 하면 편하다
        //given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring"); //멤버 이름 똑같이 spring으로 하고 join을 하면
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}