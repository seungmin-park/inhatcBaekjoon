package inhatc.inhatcbaekjoon.api;

import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class githubApiTest {

    @Autowired
    private SolvedApi solvedApi;

    @Autowired
    private GithubApi githubApi;

    @Test
    @DisplayName("repos")
    void repos() throws Exception {
        //given
        BaekJoon baekJoon = solvedApi.getUserInfo("tmddudals369");
        GithubInfo githubInfo = githubApi.userGithubCommitCountInfo("seungmin-park");
        Member member = new Member("박승민", "201844050@itc.ac.kr",githubInfo , baekJoon);
        //when
        String userGithubId = githubInfo.getUserGithubId();
        int commitCount = githubInfo.getCommitCount();
        //then
        assertThat(commitCount).isEqualTo(291);
        assertThat(userGithubId).isEqualTo("seungmin-park");
    }

}