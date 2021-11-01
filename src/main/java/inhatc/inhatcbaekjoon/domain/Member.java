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

    private String username;
    private String email;
    private String password;

    @Column(name = "auth")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGithubId")
    private GithubInfo githubInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bjname")
    private BaekJoon baekJoon;

    public Member(String name, String email, String password, String role, GithubInfo githubInfo, BaekJoon baekJoon) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.githubInfo = githubInfo;
        this.baekJoon = baekJoon;
    }
}
