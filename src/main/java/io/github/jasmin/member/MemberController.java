package io.github.jasmin.member;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/findAllMembers")
    public ResponseEntity<List<Member>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findMember(@PathVariable("id")
    @Min(value = 0, message = "id는 1보다 크거나 같아야 합니다.")
    @NotNull(message = "id는 null일 수 없습니다.") final Long id) {
        return ResponseEntity.ok(memberService.findByMemberIdx(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable("id")
    @Min(value = 0, message = "idx는 1보다 크거나 같아야 합니다.")
    @NotNull(message = "idx는 null일 수 없습니다.") final Long id,
        @Valid @RequestBody final MemberUpdateRequest request) {
        memberService.updateMember(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveMember(@RequestBody final MemberCreateRequest request) {
        memberService.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
