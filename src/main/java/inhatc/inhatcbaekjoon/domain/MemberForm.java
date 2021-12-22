package inhatc.inhatcbaekjoon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MemberForm {

    @NotNull(message = "회원 이름이 비어있습니다.")
    @NotEmpty(message = "회원 이름이 비어있습니다.")
    private String name;

    @NotNull(message = "이메일이 비어있습니다.")
    @NotEmpty(message = "이메일이 비어있습니다.")
    private String email;

    @NotNull(message = "비밀번호가 비이었습니다.")
    @NotEmpty(message = "비밀번호가 비이었습니다.")
    private String password;

    @NotNull(message = "백준아이디가 비어있습니다.")
    @NotEmpty(message = "백준아이디가 비어있습니다.")
    private String BJName;

    @NotNull(message = "github 아이디가 비어있습니다.")
    @NotEmpty(message = "github 아이디가 비어있습니다.")
    private String userGithubId;

    private String auth;
}
