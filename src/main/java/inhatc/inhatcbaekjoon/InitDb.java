package inhatc.inhatcbaekjoon;

import inhatc.inhatcbaekjoon.api.GithubApi;
import inhatc.inhatcbaekjoon.api.SolvedApi;
import inhatc.inhatcbaekjoon.domain.BaekJoon;
import inhatc.inhatcbaekjoon.domain.DataStructure;
import inhatc.inhatcbaekjoon.domain.GithubInfo;
import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.service.BaekJoonService;
import inhatc.inhatcbaekjoon.service.DataStructureService;
import inhatc.inhatcbaekjoon.service.GithubService;
import inhatc.inhatcbaekjoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final MemberService memberService;
    private final GithubService githubService;
    private final BaekJoonService baekJoonService;
    private final DataStructureService dataStructureService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SolvedApi solvedApi;
    private final GithubApi githubApi;

    @PostConstruct
    public void createMember() throws IOException, InterruptedException {
        BaekJoon baekJoon = solvedApi.getUserInfo("tmddudals369");
        GithubInfo githubInfo = githubApi.createGithubInfo("seungmin-park");
        Member member = new Member("박승민", "201844050@itc.ac.kr",bCryptPasswordEncoder.encode("11111"), "ROLE_ADMIN", githubInfo , baekJoon);
        baekJoonService.join(baekJoon);
        githubService.join(githubInfo);
        memberService.join(member);
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
        dataStructureService.join(dataStructure);
    }
}
