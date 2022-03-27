package io.github.jasmin.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.jasmin.member.controller.MemberController;
import io.github.jasmin.member.models.MemberInfo;

public class MemberTest {

    @Autowired
    MemberController memberController;

    @Test
    public void test() throws Exception{
        MemberInfo memberInfo = new MemberInfo("cust1", "password1");
        
        //memberController.insertMember(memberInfo);

        //ReqMember reqMember = new ReqMember(memberInfo.getMemberId());
        //MemberInfo memberInfo2 = memberController.selectMember(reqMember);
        
        //Assertions.assertThat(memberInfo2).isEqualTo(memberInfo);
        Assertions.assertThat(memberInfo.getSeqMember()).isEqualTo(1);
    }

}
