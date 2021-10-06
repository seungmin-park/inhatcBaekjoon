package inhatc.inhatcbaekjoon;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.domain.University;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class SolvedApiTest {

    @Autowired
    SolvedApi solvedApi;

    @Autowired
    BaekJoonService baekJoonService;

    @Autowired
    MemberService memberService;

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
        assertThat(myUniversityRank).isEqualTo(64);
        assertThat(myUniversityGlobalRank).isEqualTo(135);
        assertThat(myUniversityRating).isEqualTo(1684);

        assertThat(competitionUniversityName).isEqualTo("상명대학교(천안)");
        assertThat(competitionUniversityRank).isEqualTo(63);
        assertThat(competitionUniversityRating).isEqualTo(1685);

        assertThat(globalCompetitionUniversityName).isEqualTo("상명대학교(천안)");
        assertThat(globalCompetitionUniversityRank).isEqualTo(134);
        assertThat(globalCompetitionUniversityRating).isEqualTo(1685);
    }

    @Test
    @DisplayName("solvedCount")
    void solvedCount() throws Exception {
        //given
        BaekJoon leejuhu = solvedApi.getUserInfo("leejuhu");
        Member test1 = new Member("test1", "test@", leejuhu);
        leejuhu.setSavingSolvedCount(760);
        baekJoonService.join(leejuhu);
        memberService.join(test1);
        //when
        baekJoonService.dailySolvedCount();
        //then
        }

    }

