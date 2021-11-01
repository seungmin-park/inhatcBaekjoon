package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.domain.LoginForm;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Slf4j
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
        Member member = memberService.loadUserByUsername(loginForm.getEmail());
        log.info(member.getPassword());
        log.info(loginForm.getPassword());
        if (member.getPassword().equals(loginForm.getPassword())) {
            log.info("login succeed");
        } else {
            log.info("login failed");
        }
        return "redirect:/";
    }

    @GetMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "login/loginForm";
    }

    @GetMapping("denied")
    public String denied() {
        return "/login/denied";
    }


}
