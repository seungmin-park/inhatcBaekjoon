package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.repository.UniversityRepository;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RankingController {

    private final SolvedApi solvedApi;
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
    public String dailyRank(Model model) throws IOException, InterruptedException {
        baekJoonService.dailySolvedCount();
        List<BaekJoon> baekJoons = baekJoonService.findAllSortByTodaySolvedCount();
        model.addAttribute("baekJoons", baekJoons);
        return "rank/dailyrank";
    }
}
