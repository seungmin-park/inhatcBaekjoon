package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.domain.LoginForm;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("loginForm", new LoginForm());
//        return "/login/loginForm";
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid LoginForm loginForm, BindingResult result) {
//        Member member = memberService.findByEmail(loginForm.getEmail());
//        log.info(member.getPassword());
//        log.info(loginForm.getPassword());
//        if (member.getPassword().equals(loginForm.getPassword())) {
//            log.info("login succeed");
//        } else {
//            log.info("login failed");
//        }
//        return "redirect:/";
//    }
//

    @ResponseBody
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @ResponseBody
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/login")
    public String login() {
        return "/login/loginForm";
    }
}
