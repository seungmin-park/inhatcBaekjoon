package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.DataStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DataStructureRepository {

    private final EntityManager em;

    @Transactional
    public String save(DataStructure dataStructure) {
        em.persist(dataStructure);
        return dataStructure.getName();
    }

    public DataStructure findById(Long id) {
        return em.find(DataStructure.class, id);
    }

    public DataStructure findByName(String name) {
        return em.createQuery("select d from DataStructure d where d.name =: name", DataStructure.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public List<DataStructure> findByAll() {
        return em.createQuery("select d from DataStructure d", DataStructure.class).getResultList();
    }
}
