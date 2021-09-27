package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String BJName;
    private Integer rating;

    public Member(String name, String email, String BJName, Integer rating) {
        this.name = name;
        this.email = email;
        this.BJName = BJName;
        this.rating = rating;
    }
}
