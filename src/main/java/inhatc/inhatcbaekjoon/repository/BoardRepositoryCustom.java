package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.BoardEntity;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardEntity> findBoards(BoardSearch boardSearch);
}
