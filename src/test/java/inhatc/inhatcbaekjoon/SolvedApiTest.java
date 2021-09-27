package inhatc.inhatcbaekjoon;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.University;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SolvedApiTest {

    private SolvedApi solvedApi = new SolvedApi();

    @Test
    @DisplayName("학교순위정보")
    void 학교순위정보() throws Exception {
        //given
        University university = solvedApi.universityRank();
        //when
        int myUniversityRank = university.getMyUniversityRank();
        int myUniversityRating = university.getMyUniversityRating();
        String competitionUniversityName = university.getCompetitionUniversityName();
        int competitionUniversityRank = university.getCompetitionUniversityRank();
        int competitionUniversityRating = university.getCompetitionUniversityRating();
        //then
        assertThat(myUniversityRank).isEqualTo(63);
        assertThat(myUniversityRating).isEqualTo(1684);
        assertThat(competitionUniversityName).isEqualTo("상명대학교(천안)");
        assertThat(competitionUniversityRank).isEqualTo(62);
        assertThat(competitionUniversityRating).isEqualTo(1685);
    }
}
