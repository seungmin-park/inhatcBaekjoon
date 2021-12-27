package inhatc.inhatcbaekjoon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Slf4j
@Service
public class GithubApi {
    private String userReposUrl = "https://api.github.com/users/"; // /userId/repos
    private String testUserCommitUrl = "https://api.github.com/repos/"; // /userId/repoName/contributor
    private String userCommitUrl = "https://api.github.com/repos/"; // /userid/reposName

    private ObjectMapper obj = new ObjectMapper();


    /**
     * 전체 커밋 수 구하기
     */
    public GithubInfo userGithubCommitCountInfo(String userGithubId) throws IOException, InterruptedException {
        long beforeTime = System.currentTimeMillis();

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

        try {
            ArrayList repos = obj.readValue(response.body(), ArrayList.class);
            for (Object repo : repos) {
                LinkedHashMap hashMap = (LinkedHashMap) repo;

                stringBuilder.setLength(0);

                stringBuilder.append(testUserCommitUrl);
                stringBuilder.append(userGithubId);
                stringBuilder.append("/");
                stringBuilder.append(hashMap.get("name"));
                stringBuilder.append("/contributors");

                //api 통신 - user 의 Repository 의 commit 가져오기
                request = HttpRequest.newBuilder()
                        .uri(URI.create(stringBuilder.toString()))
                        .header("Content-Type", "application/json")
                        .header("Authorization","token {gitToken}")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                ArrayList repoCommit = obj.readValue(response.body(), ArrayList.class);
                if (repoCommit.size() == 0) {
                    stringBuilder.setLength(0);

                    stringBuilder.append(userCommitUrl);
                    stringBuilder.append(userGithubId);
                    stringBuilder.append("/");
                    stringBuilder.append(hashMap.get("name"));
                    stringBuilder.append("/commits?page=");
                    int pageNum = 1; // 한번의 통신에 최대 3o개의 commit 만 불러올 수 있음
                    while (true) {
                        //api 통신 - user 의 Repository 의 commit 가져오기
                        request = HttpRequest.newBuilder()
                                .uri(URI.create(stringBuilder.toString() + pageNum))
                                .header("Content-Type", "application/json")
                                .header("Authorization", "token {gitToken}")
                                .method("GET", HttpRequest.BodyPublishers.noBody())
                                .build();
                        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                        repoCommit = obj.readValue(response.body(), ArrayList.class);
                        if (repoCommit.size() == 30) {
                            sum += 30;
                            pageNum++;
                        } else {
                            sum += repoCommit.size();
                            pageNum = 1;
                            break;
                        }
                    }
                } else {
                    LinkedHashMap contributions = (LinkedHashMap) repoCommit.get(0);
                    sum += (int)contributions.get("contributions");
                }
            }
            long afterTime = System.currentTimeMillis();
            log.info("commit 수 {}",sum);
            log.info("commit 수 불러오는데 걸리는 시간 {}.{}초",(afterTime - beforeTime)/1000,(afterTime - beforeTime)%1000);
        } catch (Exception e) {
            return null;
        }
        return new GithubInfo(userGithubId,sum);
    }
}
