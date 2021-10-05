package inhatc.inhatcbaekjoon;

import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.Member;
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
public class MemberListSortTest {

    @Autowired
    SolvedApi solvedApi;

    @Autowired
    MemberService memberService;

    @Autowired
    BaekJoonService baekJoonService;


    @Test
    @DisplayName("rating으로정렬")
    void rating으로정렬() throws Exception {
        //given
        baekJoonService.clear();
        memberService.clear();
        BaekJoon baekJoon1 = solvedApi.getUserInfo("tmddudals369");
        BaekJoon baekJoon2 = solvedApi.getUserInfo("leejuhu");
        Member member1 = new Member("박승민","201844050@itc.ac.kr", baekJoon1);
        Member member2 = new Member("test","test@test", baekJoon2);

        baekJoonService.join(baekJoon1);
        baekJoonService.join(baekJoon2);
        memberService.join(member1);
        memberService.join(member2);
        //when
        List<Member> members = memberService.findAll();
        //then

        assertThat(members.size()).isEqualTo(2);
        assertThat(members.get(0)).isEqualTo(member2);
        assertThat(members.get(1)).isEqualTo(member1);
    }
}
