package io.github.jasmin.member;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("멤버 정보를 업데이트")
    void updateMemberTest() {
        final Member member = new Member();
        final MemberUpdateRequest updateInfo = new MemberUpdateRequest("name" ,"email@naver.com", "password");

        member.updateMember(updateInfo);

        assertThat(member.getName()).isEqualTo("name");
        assertThat(member.getEmail()).isEqualTo("email@naver.com");
        assertThat(member.getPassword()).isEqualTo("password");

    }

}