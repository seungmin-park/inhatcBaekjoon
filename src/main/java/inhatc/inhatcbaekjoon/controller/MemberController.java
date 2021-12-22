package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.*;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SolvedApi solvedApi;
    private final GithubApi githubApi;
    private final MemberService memberService;
    private final GithubService githubService;
    private final BaekJoonService baekJoonService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @SneakyThrows
    @PostMapping("/members/new")
    public void create(@Validated MemberForm memberForm, BindingResult result, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        BaekJoon baekJoon = solvedApi.getUserInfo(memberForm.getBJName());
        GithubInfo githubInfo = githubApi.userGithubCommitCountInfo(memberForm.getUserGithubId());
        PrintWriter writer = response.getWriter();
        if (baekJoon == null ) {
            writer.println("<script language='javascript'>");
            writer.print("alert('존재하지 않는 백준 아이디 입니다. \\n 조건 1) 백준에 학교 인증이 되어있는지\\n 조건 2) sovled.ac와 백준이 연동 되어있는지');");
            writer.println("history.go(-1);");
            writer.println("</script>");
            writer.flush();
        } else if (githubInfo == null) {
            writer.println("<script language='javascript'>");
            writer.print("alert('존재하지 않는 깃헙 아이디 입니다.\\n github 아이디를 다시 확인 해주시기 바랍니다.');");
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

    @SneakyThrows
    @GetMapping("/members")
    public String list(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model
    ) throws IOException, InterruptedException {
        List<Member> members = memberService.findAllSortByRating();
        baekJoonService.dailySolvedCount();
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("members", members);

        return "/members/memberList";
    }

    @SneakyThrows
    @PostConstruct
    public void createMember(){
        BaekJoon baekJoon = solvedApi.getUserInfo("tmddudals369");
        GithubInfo githubInfo = githubApi.userGithubCommitCountInfo("seungmin-park");
        Member member = new Member("박승민", "201844050@itc.ac.kr",bCryptPasswordEncoder.encode("11111"), "ROLE_ADMIN", githubInfo , baekJoon);
        baekJoonService.join(baekJoon);
        githubService.join(githubInfo);
        memberService.join(member);
    }
}
