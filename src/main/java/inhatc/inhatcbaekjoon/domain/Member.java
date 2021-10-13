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
    @JoinColumn(name = "userGithubId")
    private GithubInfo githubInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bjname")
    private BaekJoon baekJoon;

    public Member(String name, String email, GithubInfo githubInfo, BaekJoon baekJoon) {
        this.name = name;
        this.email = email;
        this.githubInfo = githubInfo;
        this.baekJoon = baekJoon;
    }
}
