package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.*;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.GithubService;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
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
        return "members/createMemberForm";
    }

    @SneakyThrows
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        BaekJoon baekJoon = solvedApi.getUserInfo(memberForm.getBJName());
        GithubInfo githubInfo = githubApi.userGithubCommitCountInfo(memberForm.getUserGithubId());
        String rawPassword = memberForm.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        Member member = new Member(memberForm.getName(), memberForm.getEmail(),encPassword, "ROLE_USER", githubInfo , baekJoon);
        baekJoonService.join(baekJoon);
        githubService.join(githubInfo);
        memberService.join(member);
        return "redirect:/login";
    }

    @SneakyThrows
    @GetMapping("/members")
    public String list(Model model) throws IOException, InterruptedException {
        List<Member> members = memberService.findAllSortByRating();
        baekJoonService.dailySolvedCount();
        model.addAttribute("members", members);

        return "members/memberList";
    }


    @PostConstruct
    public void createMember() throws IOException, InterruptedException {
        BaekJoon baekJoon = solvedApi.getUserInfo("tmddudals369");
        GithubInfo githubInfo = githubApi.userGithubCommitCountInfo("seungmin-park");
        Member member = new Member("박승민", "201844050@itc.ac.kr",bCryptPasswordEncoder.encode("123456"), "ROLE_ADMIN", githubInfo , baekJoon);
        baekJoonService.join(baekJoon);
        githubService.join(githubInfo);
        memberService.join(member);
    }
}
