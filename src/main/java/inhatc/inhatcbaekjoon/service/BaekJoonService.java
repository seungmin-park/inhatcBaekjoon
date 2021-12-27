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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaekJoonService {

    private final BaekJoonRepository baekJoonRepository;
    private final SolvedApi solvedApi;

    @Transactional
    public BaekJoon join(BaekJoon baekJoon){
        return baekJoonRepository.save(baekJoon);
    }

    public Optional<BaekJoon> findById(String id){
        return baekJoonRepository.findById(id);
    }

    public List<BaekJoon> findAll(){
        return baekJoonRepository.findAll();
    }

    public List<BaekJoon> findAllByOrderByTodaySolvedCountDesc(){
        return baekJoonRepository.findAllByOrderByTodaySolvedCountDesc();
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
