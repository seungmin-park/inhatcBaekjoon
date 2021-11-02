package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class DataStructure {

    @Id
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String detail;
    @Column(columnDefinition = "text", nullable = false)
    private String javaCode;
    @Column(columnDefinition = "text")
    private String PythonCode;

    public DataStructure(String name, String detail, String javaCode, String pythonCode) {
        this.name = name;
        this.detail = detail;
        this.javaCode = javaCode;
        PythonCode = pythonCode;
    }
}
