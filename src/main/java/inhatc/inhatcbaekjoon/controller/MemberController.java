package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.domain.MemberForm;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.GithubService;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final SolvedApi solvedApi;
    private final GithubApi githubApi;
    private final MemberService memberService;
    private final GithubService githubService;
    private final BaekJoonService baekJoonService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @SneakyThrows
    @PostMapping("/new")
    public void create(@Validated MemberForm memberForm, BindingResult result, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        BaekJoon baekJoon = solvedApi.getUserInfo(memberForm.getBJName());
        GithubInfo githubInfo = githubApi.createGithubInfo(memberForm.getUserGithubId());
        PrintWriter writer = response.getWriter();
        if (baekJoon == null) {
            result.rejectValue("baekJoon", "not");
            writer.println("<script language='javascript'>");
            writer.print("alert('???????????? ?????? ?????? ????????? ?????????. \\n ?????? 1) ????????? ?????? ????????? ???????????????\\n ?????? 2) sovled.ac??? ????????? ?????? ???????????????');");
            writer.println("history.go(-1);");
            writer.println("</script>");
            writer.flush();
        } else if (githubInfo == null) {
            writer.println("<script language='javascript'>");
            writer.print("alert('???????????? ?????? ?????? ????????? ?????????.\\n github ???????????? ?????? ?????? ???????????? ????????????.');");
            writer.println("history.go(-1);");
            writer.println("</script>");
            writer.flush();
        } else {
            String rawPassword = memberForm.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            Member member = new Member(memberForm.getName(), memberForm.getEmail(), encPassword, "ROLE_USER", githubInfo, baekJoon);
            baekJoonService.join(baekJoon);
            githubService.join(githubInfo);
            memberService.join(member);
            writer.println("<script language='javascript'>");
            writer.println("location.replace('/login');");
            writer.println("</script>");
            writer.flush();
        }
    }

    @GetMapping("")
    public String list(Model model) throws IOException, InterruptedException {
        List<Member> members = memberService.findAllByRatingDesc();
        baekJoonService.dailySolvedCount();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
