package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    @Column(name = "auth")
    private String auth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGithubId")
    private GithubInfo githubInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bjname")
    private BaekJoon baekJoon;

    public Member(String name, String email, String password, String auth, GithubInfo githubInfo, BaekJoon baekJoon) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.auth = auth;
        this.githubInfo = githubInfo;
        this.baekJoon = baekJoon;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        } return roles;

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
