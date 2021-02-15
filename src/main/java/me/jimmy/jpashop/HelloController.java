package me.jimmy.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping
    @ResponseBody
    public String index() {
        return "hello spring";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello22");
        return "hello"; // return 값은 화면 이름 => resources/templates/hello.html을 찾는다.
    }
}
