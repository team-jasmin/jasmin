package io.github.jasmin.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateRequest {

    private String name;
    private String email;
    private String password;

    public Member toEntity() {
        return Member.of(name, email, password);
    }

}
