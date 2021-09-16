package inhatc.inhatcbaekjoon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.domain.MemberForm;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @SneakyThrows
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        Member member = new Member(memberForm.getName(), memberForm.getEmail(), memberForm.getBJName(), findRatingByBJName(memberForm.getBJName()));
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);

        return "members/memberList";
    }

    private Integer findRatingByBJName(String BJName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://solved.ac/api/v3/search/user?query="+BJName))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper obj = new ObjectMapper();
        HashMap<String, Object> map = obj.readValue(response.body(), HashMap.class);
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) map.get("items");
        HashMap<String, Object> objectHashMap = list.get(0);

        return (Integer) objectHashMap.get("rating");
    }

}
