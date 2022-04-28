package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.GithubService;
import inhatc.inhatcbaekjoon.service.MemberService;
import inhatc.inhatcbaekjoon.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankingController {

    private final SolvedApi solvedApi;
    private final MemberService memberService;
    private final BaekJoonService baekJoonService;
    private final UniversityService universityService;

    @GetMapping("")
    public String rankInfo(Model model) throws IOException, InterruptedException {
        University university = solvedApi.universityRank();
        universityService.join(university);
        model.addAttribute("university", university);
        return "rank/universityrankInfo";
    }

    @SneakyThrows
    @GetMapping("/daily")
    public String dailyRank(Model model) {
        baekJoonService.dailySolvedCount();
        List<Member> members = memberService.findAllByOrderByTodaySolvedCountDesc();
        model.addAttribute("members", members);
        return "rank/dailyrank";
    }

    @GetMapping("/commit")
    public String gitCommitRank(Model model){
        List<Member> members = memberService.findAllByOrderByCommitCountDesc();
        model.addAttribute("members", members);
        return "rank/githubCommitRank";
    }
}
