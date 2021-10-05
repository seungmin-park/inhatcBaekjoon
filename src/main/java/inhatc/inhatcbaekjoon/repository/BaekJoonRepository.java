package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BaekJoon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BaekJoonRepository {

    private final EntityManager em;

    public String save(BaekJoon baekJoon) {
        em.persist(baekJoon);
        return baekJoon.getId();
    }

    public BaekJoon findById(String id) {
        return em.find(BaekJoon.class, id);
    }

    public List<BaekJoon> findAll(){
        return em.createQuery("select b from BaekJoon b", BaekJoon.class).getResultList();
    }

}
