package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(BoardEntity boardEntity) {
        em.persist(boardEntity);
        return boardEntity.getId();
    }

    public BoardEntity findById(Long id) {
        return em.find(BoardEntity.class, id);
    }

    public List<BoardEntity> findByAll() {
        return em.createQuery("select b from BoardEntity b", BoardEntity.class).getResultList();
    }

    public List<BoardEntity> findByValue(String value) {
        if (value.equals("ALL")) {
            return findByAll();
        } else {
            Category cg = Category.valueOf(value);
            return em.createQuery("select b from BoardEntity b where b.category =: category", BoardEntity.class)
                    .setParameter("category",cg)
                    .getResultList();
        }
    }

    public Long deleteById(Long id) {
        BoardEntity board = findById(id);
        em.remove(board);
        return (id);
    }
}
