package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long join(BoardEntity boardEntity) {
        return boardRepository.save(boardEntity);
    }

    public BoardEntity findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<BoardEntity> findAll() {
        return boardRepository.findByAll();
    }

    public List<BoardEntity> findByValue(String category) {
        return boardRepository.findByValue(category);
    }

    @Transactional
    public Long deleteById(Long id) {
        return boardRepository.deleteById(id);
    }
}
