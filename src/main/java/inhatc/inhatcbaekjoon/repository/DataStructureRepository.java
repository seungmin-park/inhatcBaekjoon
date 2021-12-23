package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.DataStructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataStructureRepository extends JpaRepository<DataStructure,Long> {

    public DataStructure findByName(String name);
}
