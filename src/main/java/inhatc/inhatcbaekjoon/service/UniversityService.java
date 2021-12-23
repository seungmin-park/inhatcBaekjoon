package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Transactional
    public University join(University university){
        return universityRepository.save(university);
    }

    public Optional<University> findById(Long id) {
        return universityRepository.findById(id);
    }

    public List<University> findAll() {
        return universityRepository.findAll();
    }
}
