package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GithubService {

    private final GithubRepository githubRepository;

    @Transactional
    public GithubInfo join(GithubInfo githubInfo) {
        return githubRepository.save(githubInfo);
    }

    public Optional<GithubInfo> findById(String id) {
        return githubRepository.findById(id);
    }

    public List<GithubInfo> findAll() {
        return githubRepository.findAll();
    }

    public List<GithubInfo> findAllByOrderByCommitCountDesc() {
        return githubRepository.findAllByOrderByCommitCountDesc();
    }
}
