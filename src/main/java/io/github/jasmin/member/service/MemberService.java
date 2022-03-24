package io.github.jasmin.member.service;

import io.github.jasmin.member.mapper.MemberMapper;
import io.github.jasmin.member.models.MemberInfo;
import io.github.jasmin.member.models.ReqMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    /**
     * 멤버 회원가입
     * @param memberInfo
     * @throws SQLException
     */
    public boolean insertMember(MemberInfo memberInfo) throws SQLException {
        return memberMapper.insertMember(memberInfo);
    }

    /**
     * 멤버 정보조회
     * @param reqMember
     * @return memberInfo
     * @throws SQLException
     */
    public MemberInfo selectMember(ReqMember reqMember) throws SQLException {
        return memberMapper.selectMember(reqMember);
    }

    /**
     * 멤버 전체조회
     * @return MemberInfoAll
     * @throws SQLException
     */
    public List<MemberInfo> selectMemberAll() throws SQLException {
        return memberMapper.selectMemberAll();
    }

}//class
