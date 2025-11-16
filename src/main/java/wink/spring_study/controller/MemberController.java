package wink.spring_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wink.spring_study.service.MemberService;

@Controller  // 스프링 컨테이너에 이 컨트롤러 애노테이션이 있으면 이 멤버 컨트롤로 객체를 생성해서 스프링에 넣어두고 관리
public class MemberController {
    private final MemberService memberService;
    @Autowired //스프링이 객체를 스프링 컨테이너에서 찾아서 넣어준다 (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
