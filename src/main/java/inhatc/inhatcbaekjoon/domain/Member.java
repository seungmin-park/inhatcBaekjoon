package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGithubId")
    private GithubInfo githubInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bjname")
    private BaekJoon baekJoon;

    public Member(String name, String email, String password, GithubInfo githubInfo, BaekJoon baekJoon) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.githubInfo = githubInfo;
        this.baekJoon = baekJoon;
    }
}
