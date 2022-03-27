package io.github.jasmin.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jasmin.member.models.MemberInfo;
import io.github.jasmin.member.models.ReqMember;
import io.github.jasmin.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
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

    @PostMapping("/update")
    public Integer updateMember(MemberInfo memberInfo) throws Exception {
        return memberService.updateMember(memberInfo);
    }

    @PostMapping("/delete")
    public Integer deleteMember(ReqMember reqMember) throws Exception {
        return memberService.deleteMember(reqMember);
    }

}
