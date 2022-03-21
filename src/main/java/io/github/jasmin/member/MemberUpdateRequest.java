package io.github.jasmin.member;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequest {

    @NotEmpty(message = "이름 값은 필수 값입니다.")
    private String name;

    @NotEmpty(message = "이메일 값은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;
}
