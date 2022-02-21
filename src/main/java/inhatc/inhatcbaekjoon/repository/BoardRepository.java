package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long>, BoardRepositoryCustom {
}
