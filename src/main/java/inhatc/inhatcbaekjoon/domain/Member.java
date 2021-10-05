package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bjname")
    private BaekJoon baekJoon;


    public Member(String name, String email, BaekJoon baekJoon) {
        this.name = name;
        this.email = email;
        this.baekJoon = baekJoon;
    }
}
