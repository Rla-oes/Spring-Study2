package wink.spring_study.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//spring은 이거 골뱅이 컨트롤러 해줘야한다
@Controller
public class HelloController {
    //get 방식!
    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data", "안녕~");
        return "hello"; //이 리턴값에 해당하는 페이지를 찾아서 넘겨라
    }

    @GetMapping("hello-mvc")
    //이번엔 파라미터를 넘겨보자
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에 body부에 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //객체 생성
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}