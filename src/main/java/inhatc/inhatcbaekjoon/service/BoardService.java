package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.Category;
import inhatc.inhatcbaekjoon.repository.BoardRepository;
import inhatc.inhatcbaekjoon.repository.BoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardEntity join(BoardEntity boardEntity) {
        return boardRepository.save(boardEntity);
    }

    public Optional<BoardEntity> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<BoardEntity> findAll() {
        return boardRepository.findAll();
    }

    public List<BoardEntity> findByValue(BoardSearch boardSearch) {
        return boardRepository.findBoards(boardSearch);
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
