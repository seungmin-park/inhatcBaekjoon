package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String universityName = "인하공업전문대학";
    private String competitionUniversityName;       //위 등수 학교 이름 (대학 중)
    private int myUniversityRank;                   //인하공업전문대학 순위 (대학 중)
    private int competitionUniversityRank;          //위 등수 순위 (대학 중)
    private int myUniversityRating;                 //인하공업전문대학 점수
    private int competitionUniversityRating;        //위 등수 학교 점수
    private int ratingDifference;                   //위 등수 학교와 인하공업전문대학 점수 차이(대학 중)

    private String globalCompetitionUniversityName;//위 등수 학교 이름 (전체 기관)
    private int myUniversityGlobalRank;             //인하공업전문대학 순위 (전체 기관)
    private int globalCompetitionUniversityRank;    //위 등수 학교 순위 (대학 중)
    private int globalCompetitionUniversityRating;  //위 등수 학교 점수 (전체 기관)
    private int globalRatingDifference;             //위 등수 학교와 인하공업전문대학 점수 차이(전체 기관)


    public University() {
    }

    /**
     * 대학 중 랭킹 정보
     */
    public void universityRankingInfo(String competitionUniversityName, int myUniversityRank, int competitionUniversityRank, int myUniversityRating, int competitionUniversityRating, int ratingDifference) {
        this.competitionUniversityName = competitionUniversityName;
        this.myUniversityRank = myUniversityRank;
        this.competitionUniversityRank = competitionUniversityRank;
        this.myUniversityRating = myUniversityRating;
        this.competitionUniversityRating = competitionUniversityRating;
        this.ratingDifference = ratingDifference;
    }

    /**
     * 전체 랭킹 정보
     */
    public void globalRankingInfo(String globalCompetitionUniversityName, int myUniversityGlobalRank, int globalCompetitionUniversityRank, int globalCompetitionUniversityRating, int globalRatingDifference) {
        this.globalCompetitionUniversityName = globalCompetitionUniversityName;
        this.myUniversityGlobalRank = myUniversityGlobalRank;
        this.globalCompetitionUniversityRank = globalCompetitionUniversityRank;
        this.globalCompetitionUniversityRating = globalCompetitionUniversityRating;
        this.globalRatingDifference = globalRatingDifference;
    }
}
