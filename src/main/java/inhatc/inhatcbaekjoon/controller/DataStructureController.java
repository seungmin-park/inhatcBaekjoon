package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.DataStructure;
import inhatc.inhatcbaekjoon.repository.DataStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataStructureController {

    private final DataStructureRepository dataStructureRepository;

    @GetMapping("/dataStructure/view")
    public String viewDataStructure(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model
    ) {
        List<DataStructure> structureList = dataStructureRepository.findByAll();
        model.addAttribute("userRole", principalDetails.getUserRole());
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("structureList", structureList);
        return "/dataStructure/viewDataStructure";
    }

    @PostConstruct
    public void preCreate() {
        DataStructure dataStructure = new DataStructure("자료구조이름", "자세한 설명", "java구현코드", null);
        dataStructureRepository.save(dataStructure);
    }
}
