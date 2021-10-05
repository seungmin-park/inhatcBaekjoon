package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BaekJoon {

    @Id
    @Column(name = "BJName")
    private String id;
    private Integer rating;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    public BaekJoon(String id, Integer rating, Tier tier) {
        this.id = id;
        this.rating = rating;
        this.tier = tier;
    }
}
