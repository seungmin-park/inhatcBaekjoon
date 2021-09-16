package inhatc.inhatcbaekjoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SolvedApiTest {

    @Test
    @DisplayName("apiTest")
    void apiTest() throws Exception {
        //given
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://solved.ac/api/v3/search/user?query=tmddudals369"))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //when
        ObjectMapper obj = new ObjectMapper();
        try{
            HashMap<String, Object> map = obj.readValue(response.body(), HashMap.class);
            ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) map.get("items");
            HashMap<String, Object> getItems = list.get(0);
            Integer rating = (Integer) getItems.get("rating");
            assertThat(rating).isEqualTo(600);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        //then
    }
}
