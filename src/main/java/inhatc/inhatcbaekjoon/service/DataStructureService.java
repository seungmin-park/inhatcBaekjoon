package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.DataStructure;
import inhatc.inhatcbaekjoon.repository.DataStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DataStructureService {

    private final DataStructureRepository dataStructureRepository;

    @Transactional
    public DataStructure join(DataStructure dataStructure) {
        return dataStructureRepository.save(dataStructure);
    }

    public Optional<DataStructure> findById(Long id) {
        return dataStructureRepository.findById(id);
    }

    public DataStructure findByName(String name) {
        return dataStructureRepository.findByName(name);
    }

    public List<DataStructure> findByAll() {
        return dataStructureRepository.findAll();
    }
}
