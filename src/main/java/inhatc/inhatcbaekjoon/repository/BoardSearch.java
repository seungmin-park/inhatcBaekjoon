package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearch {

    private String title; //게시물 내용
    private Category category;// 게시물 종류[STUDY,STUDY,TEAM_PROJECT,HACKATHON,CONTEST]
}
