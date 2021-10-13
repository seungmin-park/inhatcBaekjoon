package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.GithubInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GithubRepository {

    private final EntityManager em;

    public String save(GithubInfo githubInfo){
        em.persist(githubInfo);
        return githubInfo.getUserGithubId();
    }

    public GithubInfo findById(String id) {
        return em.find(GithubInfo.class, id);
    }

    public List<GithubInfo> findAll() {
        return em.createQuery("select g from GithubInfo g", GithubInfo.class).getResultList();
    }

    public List<GithubInfo> findAllSortingByCommitCount(){
        return em.createQuery("select g from GithubInfo g order by g.commitCount desc ", GithubInfo.class).getResultList();
    }
}
