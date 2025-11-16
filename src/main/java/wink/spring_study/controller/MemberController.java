package wink.spring_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import wink.spring_study.domain.Member;
import wink.spring_study.service.MemberService;

import java.util.List;

@Controller // 스프링 컨테이너에 이 컨트롤러 애노테이션이 있으면 이 멤버 컨트롤로 객체를 생성해서 스프링에 넣어두고 관리
public class MemberController {
    private final MemberService memberService;
    @Autowired  // 스프링이 객체를 스프링 컨테이너에서 찾아서 넣어준다 (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm"; //createMemberForm으로 이동(템플릿에서 찾음)
    }
    @PostMapping(value = "/members/new") //html 코드에서 post로 넘어온게 여기로 감
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member); //save를 타고 들어감
        return "redirect:/"; //회원 가입하고 홈화면으로 보내버림
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
