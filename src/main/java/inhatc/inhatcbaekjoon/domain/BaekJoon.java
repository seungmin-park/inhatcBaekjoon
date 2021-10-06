package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaekJoon {

    @Id
    @Column(name = "BJName")
    private String id;
    private Integer rating;
    private Integer totalSolvedCount;
    private int savingSolvedCount;
    private int todaySolvedCount;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    public BaekJoon(String id, Integer rating, Integer totalSolvedCount, int savingSolvedCount, Tier tier) {
        this.id = id;
        this.rating = rating;
        this.totalSolvedCount = totalSolvedCount;
        this.savingSolvedCount = savingSolvedCount;
        this.tier = tier;
    }
}
