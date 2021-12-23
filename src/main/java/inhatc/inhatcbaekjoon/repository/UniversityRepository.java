package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {

}
