package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.DataStructure;
import inhatc.inhatcbaekjoon.service.DataStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class DataStructureController {

    private final DataStructureService dataStructureService;

    @GetMapping("/dataStructure")
    public String viewDataStructure(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model
    ) {
        List<DataStructure> structureList = dataStructureService.findByAll();
        model.addAttribute("userRole", principalDetails.getUserRole());
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("structureList", structureList);
        return "dataStructure/viewDataStructure";
    }

    @GetMapping("/modify/dataStructure/{name}")
    public String modify(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable String name,
            Model model
    ) {
        DataStructure structure = dataStructureService.findByName(name);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("structure", structure);
        return "dataStructure/modifyDataStructure";
    }

    @PutMapping("/modify/dataStructure/{name}")
    public String modify(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable String name,
            @RequestParam String title,
            @RequestParam String detail,
            @RequestParam String javaCode,
            Model model
    ) {
        DataStructure structure = dataStructureService.findByName(name);
        structure.modifyDataStructure(title,detail ,javaCode);
        model.addAttribute("username", principalDetails.getUsername());
        dataStructureService.join(structure);
        return "redirect:/view/dataStructure";
    }


}
