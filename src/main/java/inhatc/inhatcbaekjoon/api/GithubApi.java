package inhatc.inhatcbaekjoon.api;

import inhatc.inhatcbaekjoon.domain.GithubInfo;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Slf4j
@Service
public class GithubApi {

    /**
     * 회원가입시 입력되는 유저의 github 아이디를 통해 유저 개인 repo commit 합 구하기
     */
    public GithubInfo createGithubInfo(String userGitName) throws IOException, InterruptedException {
        long beforeTime = System.currentTimeMillis();
        int commitCount = getCommitCount(userGitName);
        long afterTime = System.currentTimeMillis();
        log.info("commit 수 {}",commitCount);
        log.info("commit 수 불러오는데 걸리는 시간 {}.{}초",(afterTime - beforeTime)/1000,(afterTime - beforeTime)%1000);

        return new GithubInfo(userGitName,commitCount);
    }

    /**
     * commit count 구하기
     * @param userGitName
     * @return
     * @throws IOException
     */
    public int getCommitCount(String userGitName) throws IOException{
        GitHub gitHub = GitHub.connect();
        int commitCount = 0;

        GHUser user = gitHub.getUser(userGitName);
        Map<String, GHRepository> repositories = user.getRepositories();

        for (GHRepository repository : repositories.values()) {

            //repo owner 찾기
            PagedIterable<GHRepository.Contributor> contributors = repository.listContributors();
            String repoOwner = contributors.toList().get(0).getLogin();
            
            //fork repo 제외
            if (repoOwner.equals(userGitName)) {
                commitCount += repository.listCommits().toList().size();
            }
        }

        return commitCount;
    }
}
