package inhatc.inhatcbaekjoon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.Tier;
import inhatc.inhatcbaekjoon.domain.University;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class SolvedApi {
    public SolvedApi() {
    }

    private String userInfoUrl = "https://solved.ac/api/v3/user/show?handle=";
    private String universityInfoUrl = "https://solved.ac/api/v3/ranking/organization?";

    ObjectMapper obj = new ObjectMapper();

    public BaekJoon getUserInfo(String BJName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userInfoUrl+BJName))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        HashMap<String, Object> paramMap = obj.readValue(response.body(), HashMap.class);

        Tier[] tiers = Tier.values();
        Tier tier = tiers[(int) paramMap.get("tier") - 1];
        int rating = (int) paramMap.get("rating");
        int solvedCount = solvedCount(BJName);
        return new BaekJoon(BJName,rating, solvedCount, solvedCount, tier);
    }

    /**
     * 학교 랭킹
     */
    public University universityRank() throws IOException, InterruptedException {
        int rank = -1;
        int rating = -1;
        String name;
        int comRating;
        University university = new University();
        //api
        String[] queryValue = {"type=university", "page=2"};
        for (int i = 0; i < queryValue.length; i ++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(universityInfoUrl + queryValue[i]))
                    .header("Content-Type", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            HashMap<String, Object> paramMap = obj.readValue(response.body(), HashMap.class);
            ArrayList<LinkedHashMap> items = (ArrayList<LinkedHashMap>) paramMap.get("items");

            //인하공업전문대학 탐색
            for (LinkedHashMap item : items) {
                if (item.get("name").equals("인하공업전문대학")) {
                    rank = (int) item.get("rank");
                    rating = (int) item.get("rating");
                    int index = (rank - 2) < 100 ? (rank - 2) : (rank - 102);
                    name =(String) items.get(index).get("name");
                    comRating =(int) items.get(index).get("rating");
                    if (i==0){
                        university.universityRankingInfo(name,rank,rank-1,rating,comRating,comRating-rating);
                    }else{
                        university.globalRankingInfo(name,rank,rank-1,comRating,comRating-rating);
                    }
                    break;
                }
            }
        }
        return university;
    }

    /**
     * solvedCount 가저오기
     */
    public int solvedCount(String BJName) throws IOException, InterruptedException {
        int totalCount = 0;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://solved.ac/api/v3/user/problem_stats?handle="+BJName))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ArrayList<LinkedHashMap> arrayList = obj.readValue(response.body(), ArrayList.class);

        for (LinkedHashMap map : arrayList) {
            totalCount += (int)map.get("solved");
        }

        return totalCount;
    }
}
