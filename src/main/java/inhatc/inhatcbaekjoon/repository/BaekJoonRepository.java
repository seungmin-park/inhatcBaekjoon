package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BaekJoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaekJoonRepository extends JpaRepository<BaekJoon,String> {

    List<BaekJoon> findAllByOrderByTodaySolvedCountDesc();
}
