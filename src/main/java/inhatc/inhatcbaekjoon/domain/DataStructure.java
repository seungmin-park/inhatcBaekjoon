package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DataStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String detail;
    @Column(columnDefinition = "text", nullable = false)
    private String javaCode;

    public void modifyDataStructure(String name, String detail, String javaCode) {
        this.name = name;
        this.detail = detail;
        this.javaCode = javaCode;
    }

    public DataStructure(String name, String detail, String javaCode) {
        this.name = name;
        this.detail = detail;
        this.javaCode = javaCode;
    }

}
