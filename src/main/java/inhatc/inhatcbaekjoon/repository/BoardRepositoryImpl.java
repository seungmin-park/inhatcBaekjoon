package inhatc.inhatcbaekjoon.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static inhatc.inhatcbaekjoon.domain.QBoardEntity.boardEntity;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardEntity> findBoards(BoardSearch boardSearch) {
        return queryFactory
                .selectFrom(boardEntity)
                .where(boardEntityTitleContain(boardSearch.getTitle())
                        , boardEntityCategoryEq(boardSearch.getCategory()))
                .fetch();
    }

    private BooleanExpression boardEntityCategoryEq(Category category) {
        return category != null ? boardEntity.category.eq(category) : null;
    }

    private BooleanExpression boardEntityTitleContain(String title) {
        if (!StringUtils.hasText(title)) {
            return null;
        }
        return boardEntity.title.contains(title);
    }
}
