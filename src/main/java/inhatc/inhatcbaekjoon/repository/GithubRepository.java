package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.GithubInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface GithubRepository extends JpaRepository<GithubInfo,String> {

    public List<GithubInfo> findAllByOrderByCommitCountDesc();
}
