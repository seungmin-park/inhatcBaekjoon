package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m order by m.baekJoon.rating desc ")
    List<Member> findAllByRatingDesc();


    Member findByEmail(String email);
}
