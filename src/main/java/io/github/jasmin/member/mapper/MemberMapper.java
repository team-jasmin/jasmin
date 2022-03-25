package io.github.jasmin.member.mapper;

import io.github.jasmin.member.models.MemberInfo;
import io.github.jasmin.member.models.ReqMember;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface MemberMapper {
    
    /** 멤버 회원가입 */
    boolean insertMember(MemberInfo memberInfo) throws SQLException;

    /** 멤버 정보조회 */
    MemberInfo selectMember(ReqMember reqMember) throws SQLException;

    /** 멤버 전체조회 */
    List<MemberInfo> selectMemberAll() throws SQLException;

    /** 멤버 정보업데이트 */
    Integer updateMember(MemberInfo memberInfo) throws SQLException;

    /** 멤버 정보삭제 */
    Integer deleteMember(ReqMember reqMember) throws SQLException;
}
