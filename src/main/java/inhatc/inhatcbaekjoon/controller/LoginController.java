package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.domain.LoginForm;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "/login/loginForm";
    }



    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm) {
        Member member = memberService.findByEmail(loginForm.getEmail());
        if (member.getPassword().equals(new BCryptPasswordEncoder().encode(loginForm.getPassword()))) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
