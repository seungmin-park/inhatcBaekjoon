package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.repository.BaekJoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaekJoonService {

    private final BaekJoonRepository baekJoonRepository;
    private final SolvedApi solvedApi;
    private LocalDateTime localDateTime;

    @Transactional
    public String join(BaekJoon baekJoon){
        return baekJoonRepository.save(baekJoon);
    }

    public BaekJoon findById(String id){
        return baekJoonRepository.findById(id);
    }

    public List<BaekJoon> findAll(){
        return baekJoonRepository.findAll();
    }

    public List<BaekJoon> findAllSortByTodaySolvedCount(){
        return baekJoonRepository.findAllSortByTodaySolvedCount();
    }

    @Transactional
    public void dailySolvedCount() throws IOException, InterruptedException {
        List<BaekJoon> baekJoons = findAll();
        for (BaekJoon baekJoon : baekJoons) {
            baekJoon.setTotalSolvedCount(solvedApi.solvedCount(baekJoon.getId()));
            baekJoon.setTodaySolvedCount(baekJoon.getTotalSolvedCount() - baekJoon.getSavingSolvedCount());
        }
    }

    @Scheduled(cron = "0 0 00 * * ?")
    @Transactional
    public void initDailyCount(){
        List<BaekJoon> baekJoons = findAll();
        for (BaekJoon baekJoon : baekJoons) {
            baekJoon.setTodaySolvedCount(0);
        }
    }

}
