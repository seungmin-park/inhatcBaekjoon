package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.repository.UniversityRepository;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RankingController {

    private final SolvedApi solvedApi;
    private final GithubApi githubApi;
    private final GithubService githubService;
    private final BaekJoonService baekJoonService;
    private final UniversityRepository universityRepository;

    @GetMapping("/rank")
    public String rankInfo(Model model) throws IOException, InterruptedException {
        University university = solvedApi.universityRank();
        universityRepository.save(university);
        model.addAttribute("university", university);
        return "rank/universityrankInfo";
    }

    @SneakyThrows
    @GetMapping("/rank/daily")
    public String dailyRank(Model model) {
        baekJoonService.dailySolvedCount();
        List<BaekJoon> baekJoons = baekJoonService.findAllSortByTodaySolvedCount();
        model.addAttribute("baekJoons", baekJoons);
        return "rank/dailyrank";
    }

    @GetMapping("/rank/commit")
    public String gitCommitRank(Model model){
        List<GithubInfo> usersGithubInfo = githubService.findAllSortingByCommitCount();
        model.addAttribute("usersGithubInfo", usersGithubInfo);
        return "rank/githubCommitRank";
    }
}
