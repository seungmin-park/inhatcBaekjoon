package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class MemberForm {

    @NotEmpty(message = "회원 이름이 비어있습니다.")
    private String name;

    @NotEmpty(message = "이메일이 비어있습니다.")
    private String email;

    @NotEmpty(message = "백준아이디가 비어있습니다.")
    private String BJName;

    @NotEmpty(message = "github 아이디가 비어있습니다.")
    private String userGithubId;
}
