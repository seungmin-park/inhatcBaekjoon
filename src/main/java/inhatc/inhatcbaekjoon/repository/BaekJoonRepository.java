package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BaekJoon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaekJoonRepository {

    String save(BaekJoon baekJoon);

    BaekJoon findById(String id);

    List<BaekJoon> findAll();

    List<BaekJoon> findAllSortByTodaySolvedCount();
}
