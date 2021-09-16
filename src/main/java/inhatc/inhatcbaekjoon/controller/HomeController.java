package inhatc.inhatcbaekjoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// localhost:8282/ 접속시 home.html 호출
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}