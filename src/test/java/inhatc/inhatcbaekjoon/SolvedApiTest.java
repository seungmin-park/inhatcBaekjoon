package inhatc.inhatcbaekjoon;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.University;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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

        String globalCompetitionUniversityName = university.getGlobalCompetitionUniversityName();
        int myUniversityGlobalRank = university.getMyUniversityGlobalRank();
        int globalCompetitionUniversityRank = university.getGlobalCompetitionUniversityRank();
        int globalCompetitionUniversityRating = university.getGlobalCompetitionUniversityRating();
        //then
        assertThat(myUniversityRank).isEqualTo(63);
        assertThat(myUniversityGlobalRank).isEqualTo(133);
        assertThat(myUniversityRating).isEqualTo(1684);

        assertThat(competitionUniversityName).isEqualTo("상명대학교(천안)");
        assertThat(competitionUniversityRank).isEqualTo(62);
        assertThat(competitionUniversityRating).isEqualTo(1685);

        assertThat(globalCompetitionUniversityName).isEqualTo("상명대학교(천안)");
        assertThat(globalCompetitionUniversityRank).isEqualTo(132);
        assertThat(globalCompetitionUniversityRating).isEqualTo(1685);
    }
}
