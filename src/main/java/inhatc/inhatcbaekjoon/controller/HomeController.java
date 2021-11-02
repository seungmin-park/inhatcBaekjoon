package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        String username = principalDetails.getUsername();
        model.addAttribute("username", username);
        return "home";
    }

}