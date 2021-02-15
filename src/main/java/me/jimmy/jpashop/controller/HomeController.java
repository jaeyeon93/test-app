package me.jimmy.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("home controller log");
        return "home"; // home.html을 찾는다.
    }
}
