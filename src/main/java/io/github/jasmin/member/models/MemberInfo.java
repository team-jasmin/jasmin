package io.github.jasmin.member.models;

import lombok.Getter;

@Getter
public class MemberInfo {

    /** 멤버고유번호 */
    private Long seqMember;
    /** 멤버아이디 */
    private String memberId;
    /** 멤버비밀번호 */
    private String memberPwd;

}
