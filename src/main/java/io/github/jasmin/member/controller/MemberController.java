package io.github.jasmin.member.controller;

import io.github.jasmin.member.models.MemberInfo;
import io.github.jasmin.member.models.ReqMember;
import io.github.jasmin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/insert")
    public boolean insertMember(MemberInfo memberInfo) throws Exception {
        return memberService.insertMember(memberInfo);
    }

    @GetMapping("/select")
    public MemberInfo selectMember(ReqMember reqMember) throws Exception {
        return memberService.selectMember(reqMember);
    }

    @GetMapping("/selectAll")
    public List<MemberInfo> selectMember() throws Exception {
        return memberService.selectMemberAll();
    }

}//class
