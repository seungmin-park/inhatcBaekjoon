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
    public String viewDataStructure(Model model) {
        List<DataStructure> structureList = dataStructureService.findByAll();
        model.addAttribute("structureList", structureList);
        return "dataStructure/viewDataStructure";
    }

    @GetMapping("/modify/dataStructure/{name}")
    public String modify(@PathVariable String name, Model model) {
        DataStructure structure = dataStructureService.findByName(name);
        model.addAttribute("structure", structure);
        return "dataStructure/modifyDataStructure";
    }

    @PutMapping("/modify/dataStructure/{name}")
    public String modify(
            @PathVariable String name,
            @RequestParam String title,
            @RequestParam String detail,
            @RequestParam String javaCode,
            Model model
    ) {
        DataStructure structure = dataStructureService.findByName(name);
        structure.modifyDataStructure(title,detail ,javaCode);
        dataStructureService.join(structure);
        return "redirect:/view/dataStructure";
    }


}
