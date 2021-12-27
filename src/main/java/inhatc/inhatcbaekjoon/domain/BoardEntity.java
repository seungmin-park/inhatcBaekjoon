package inhatc.inhatcbaekjoon.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void modifyBoardEntity(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.modifiedDate = LocalDateTime.now();
    }

    @Builder
    public BoardEntity(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = createdDate;
    }
}
