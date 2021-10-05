package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.domain.MemberForm;
import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final BaekJoonService baekJoonService;
    private final SolvedApi solvedApi;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @SneakyThrows
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        BaekJoon baekJoon = solvedApi.getUserInfo(memberForm.getBJName());
        Member member = new Member(memberForm.getName(), memberForm.getEmail(), baekJoon);
        baekJoonService.join(baekJoon);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);

        return "members/memberList";
    }


    @GetMapping("/rank")
    public String rankInfo(Model model) throws IOException, InterruptedException {
        University university = solvedApi.universityRank();
        model.addAttribute("university", university);
        return "rank/rankInfo";
    }

    @PostConstruct
    public void createMember() throws IOException, InterruptedException {
        BaekJoon baekJoon = solvedApi.getUserInfo("tmddudals369");
        Member member = new Member("박승민","201844050@itc.ac.kr", baekJoon);
        baekJoonService.join(baekJoon);
        memberService.join(member);
    }
}
