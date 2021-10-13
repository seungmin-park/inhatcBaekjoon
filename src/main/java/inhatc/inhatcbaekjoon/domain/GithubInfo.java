package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class GithubInfo {

    @Id
    @Column(name = "userGithubId")
    private String userGithubId;
    private int commitCount;

    public GithubInfo(String userGithubId, int commitCount) {
        this.userGithubId = userGithubId;
        this.commitCount = commitCount;
    }
}
