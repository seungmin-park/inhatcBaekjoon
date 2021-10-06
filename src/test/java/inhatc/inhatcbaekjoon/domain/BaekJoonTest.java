package inhatc.inhatcbaekjoon.domain;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BaekJoonTest {

    @Autowired
    SolvedApi solvedApi;

    @Autowired
    BaekJoonService baekJoonService;

    @Autowired
    MemberService memberService;


    @Test
    @DisplayName("회원가입")
    void 회원가입() throws Exception {
        //given
        BaekJoon BJMember1 = solvedApi.getUserInfo("tmddudals369");
        String BJMemberId = baekJoonService.join(BJMember1);
        //when
        BaekJoon findBJMember = baekJoonService.findById(BJMemberId);
        //then
        assertThat(findBJMember.getRating()).isEqualTo(605);
        assertThat(findBJMember.getTier()).isEqualTo(Tier.Silver_II);
    }
    @Test
    @DisplayName("문제풀고_다음_위치변환_테스트")
    void 문제풀고_다음_위치변환_테스트() throws Exception {
        //given
        BaekJoon baekJoon = solvedApi.getUserInfo("leejuhu");
        Member member = new Member("test", "test@test", baekJoon);
        baekJoonService.join(baekJoon);
        memberService.join(member);
        //when
        List<BaekJoon> all = baekJoonService.findAll();
        System.out.println("!all = " + all);
        baekJoonService.dailySolvedCount();
        System.out.println("@all = " + all);
        List<BaekJoon> baekJoons = baekJoonService.findAllSortByTodaySolvedCount();
        //then
        System.out.println("baekJoons = " + baekJoons);
    }
}
