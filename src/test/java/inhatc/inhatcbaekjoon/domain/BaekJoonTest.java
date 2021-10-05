package inhatc.inhatcbaekjoon.domain;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}
