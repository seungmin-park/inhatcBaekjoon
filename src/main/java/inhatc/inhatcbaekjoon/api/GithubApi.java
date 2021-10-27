package inhatc.inhatcbaekjoon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class GithubApi {
    private String userReposUrl = "https://api.github.com/users/"; // /userId/repos
    private String userCommitUrl = "https://api.github.com/repos/"; // /userid/reposName

    private ObjectMapper obj = new ObjectMapper();

    /**
     * 전체 커밋 수 구하기
     */
    public GithubInfo userGithubCommitCountInfo(String userGithubId) throws IOException, InterruptedException {

        int sum = 0;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(userReposUrl);
        stringBuilder.append(userGithubId);
        stringBuilder.append("/repos");

        //api 통신 - user 의 public Repository 가져오기
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(stringBuilder.toString()))
                .header("Content-Type", "application/json")
                .header("Authorization","token {gitToken}")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ArrayList repos = obj.readValue(response.body(), ArrayList.class);
        for (Object repo : repos) {
            LinkedHashMap hashMap = (LinkedHashMap) repo;

            stringBuilder.setLength(0);

            stringBuilder.append(userCommitUrl);
            stringBuilder.append(userGithubId);
            stringBuilder.append("/");
            stringBuilder.append(hashMap.get("name"));
            stringBuilder.append("/commits?page=");

            int pageNum = 1; // 한번의 통신에 최대 3o개의 commit 만 불러올 수 있음
            while (true)
            {
                //api 통신 - user 의 Repository 의 commit 가져오기
                request = HttpRequest.newBuilder()
                        .uri(URI.create(stringBuilder.toString()+pageNum))
                        .header("Content-Type", "application/json")
                        .header("Authorization","token {gitToken}")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                ArrayList repoCommit = obj.readValue(response.body(), ArrayList.class);
                if (repoCommit.size() == 30) {
                    sum += 30;
                    pageNum++;
                } else {
                    sum += repoCommit.size();
                    pageNum = 1;
                    break;
                }
            }

        }
        return new GithubInfo(userGithubId,sum);
    }
}
