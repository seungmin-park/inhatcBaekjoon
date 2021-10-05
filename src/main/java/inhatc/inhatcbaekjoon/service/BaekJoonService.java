package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.repository.BaekJoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaekJoonService {

    private final BaekJoonRepository baekJoonRepository;

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

}
