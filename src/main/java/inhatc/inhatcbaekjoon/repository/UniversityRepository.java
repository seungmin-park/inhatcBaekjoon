package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UniversityRepository {

    private final EntityManager em;

    @Transactional
    public Long save(University university){
        em.persist(university);
        return university.getId();
    }

    public University findById(Long id) {
        return em.find(University.class, id);
    }

    public List<University> findAll() {
        return em.createQuery("select u from University u", University.class).getResultList();
    }
}
