package wink.spring_study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wink.spring_study.repository.MemberRepository;
import wink.spring_study.repository.MemoryMemberRepository;
import wink.spring_study.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean //자바 코드로 스프링 빈을 설정
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
