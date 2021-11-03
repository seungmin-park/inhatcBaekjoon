package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.DataStructure;
import inhatc.inhatcbaekjoon.repository.DataStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataStructureController {

    private final DataStructureRepository dataStructureRepository;

    @GetMapping("/view/dataStructure")
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

    @GetMapping("/view/modify/dataStructure/{name}")
    public String modify(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable String name,
            Model model
    ) {
        DataStructure structure = dataStructureRepository.findByName(name);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("structure", structure);
        return "dataStructure/modifyDataStructure";
    }

    @PutMapping("/view/modify/dataStructure/{name}")
    public String modify(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable String name,
            @RequestParam String title,
            @RequestParam String detail,
            @RequestParam String javaCode,
            Model model
    ) {
        DataStructure structure = dataStructureRepository.findByName(name);
        structure.modifyDataStructure(title,detail ,javaCode);
        model.addAttribute("username", principalDetails.getUsername());
        dataStructureRepository.save(structure);
        return "redirect:/view/dataStructure";
    }

    @PostConstruct
    public void preCreate() {
        DataStructure dataStructure = new DataStructure("이진 트리",
                "이진트리는 컴퓨터 응용에서 가장 많이 활용되는 아주 중요한 트리구조이다. 이 이진 트리는 모든 노드가 정확하게 두 개의 서브트리를 가지고 있는 트리이다. 다만 서브트리는 공백이 될 수 있다.  즉 노드의 유한 집합으로서 공백이거나 루트와 두 개의 분리된 이진트리인 경우 왼쪽서브트리와 오른쪽 서브트리로 구성된다.  여기서 중요한점은 왼쪽과 오른쪽 서브트리를 확실하게 구분한다는 것이다.", "package Java;\n" +
                "\n" +
                "class Node{\n" +
                "    int data;\n" +
                "    Node left;\n" +
                "    Node right;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "class Tree{\n" +
                "    public Node root;\n" +
                "\n" +
                "    public void setRoot(Node node){\n" +
                "        this.root = node;\n" +
                "    }\n" +
                "\n" +
                "    public Node getRoot(){\n" +
                "        return root;\n" +
                "    }\n" +
                "\n" +
                "    public Node makeNode(Node left, int data, Node right){\n" +
                "        Node node = new Node();\n" +
                "        node.data = data;\n" +
                "        node.left = left;\n" +
                "        node.right = right;\n" +
                "        return node;\n" +
                "    }\n" +
                "    public void inorder(Node node){\n" +
                "        if(node != null){\n" +
                "            inorder(node.left);\n" +
                "            System.out.println(node.data);\n" +
                "            inorder(node.right);\n" +
                "        }\n" +
                "    }\n" +
                "    public void preorder(Node node){\n" +
                "        if(node != null){\n" +
                "            System.out.println(node.data);\n" +
                "            preorder(node.left);\n" +
                "            preorder(node.right);\n" +
                "        }\n" +
                "    }\n" +
                "    public void postorder(Node node){\n" +
                "        if(node != null){\n" +
                "            postorder(node.left);\n" +
                "            postorder(node.right);\n" +
                "            System.out.println(node.data);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "public class binary_tree {\n" +
                "    public static void main(String[] args) {\n" +
                "        Tree t = new Tree();   \n" +
                "        Node n5 = t.makeNode(null, 5, null);\n" +
                "        Node n4 = t.makeNode(null, 4, null);\n" +
                "        Node n3 = t.makeNode(null, 3, null);\n" +
                "        Node n2 = t.makeNode(n4, 2, n5);\n" +
                "        Node n1 = t.makeNode(n2, 1, n3);\n" +
                "        t.setRoot(n1);\n" +
                "        t.postorder(t.getRoot());\n" +
                "    }\n" +
                "    \n" +
                "}\n");
        dataStructureRepository.save(dataStructure);
    }
}
