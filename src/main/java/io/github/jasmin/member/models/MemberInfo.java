package io.github.jasmin.member.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfo {

    /** 멤버고유번호 */
    private Long seqMember = 0L;
    /** 멤버아이디 */
    private String memberId;
    /** 멤버비밀번호 */
    private String memberPwd;

    public MemberInfo(String memberId, String memberPwd) {
        this.seqMember += 1L;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }
}
