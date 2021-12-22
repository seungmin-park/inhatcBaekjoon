package inhatc.inhatcbaekjoon.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Slf4j
@Component
public class NewGithubApi {

    private String userReposUrl = "https://api.github.com/users/"; // /userId/repos
    private String testUserCommitUrl = "https://api.github.com/repos/"; // /userId/repoName/contributor
    private String userCommitUrl = "https://api.github.com/repos/"; // /userid/reposName

    public List<String> userRepos(String userGithubId) {

        ArrayList<String> repos = new ArrayList<>();

        try {
            URL url = new URL(userReposUrl + userGithubId + "/repos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Authorization","token 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");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser jsonParser = new JSONParser();
                ArrayList getRepoToApi = (ArrayList) jsonParser.parse(inline);

                for (Object repo : getRepoToApi) {
                    LinkedHashMap hashMap = (LinkedHashMap) repo;
                    String repoName = (String)hashMap.get("name");
                    repos.add(repoName);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return repos;
        }
    }

    public int getCommitCount(String userGithubId, ArrayList<String> repos) {

        int CommitCount = 0;

        for (String repo : repos) {
            try {

                URL url = new URL(testUserCommitUrl + userGithubId + "/" + repo + "/contributors");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setRequestProperty("Authorization","token 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");

                int responseCode = conn.getResponseCode();

                if (responseCode == 404) {
                    url = new URL(testUserCommitUrl + userGithubId + "/" + repo + "/commits?page=");
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");
                    conn.setRequestProperty("Authorization","token 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");

                } else if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {

            }
        }


        return CommitCount;
    }
}
