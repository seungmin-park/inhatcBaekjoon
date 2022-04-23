package inhatc.inhatcbaekjoon.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GithubApiTest {

    @Test
    @DisplayName("test")
    void test() throws Exception {
        //given
        GithubApi githubApi = new GithubApi();
        //when
        int commitCount = githubApi.getCommitCount("seungmin-park");
        //then
        Assertions.assertThat(commitCount).isEqualTo(395);
    }
}
