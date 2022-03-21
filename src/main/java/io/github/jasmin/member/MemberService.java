package io.github.jasmin.member;

import io.github.jasmin.exception.NotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Member findByMemberIdx(final long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("해당하는 멤버를 찾을 수 없습니다."));
    }

    public void updateMember(final Long id, final MemberUpdateRequest request) {
        final Member member = findByMemberIdx(id);
        member.updateMember(request);
    }

    public void saveMember(final MemberCreateRequest request) {
        memberRepository.save(request.toEntity());
    }

    public void deleteById(final Long id) {
        memberRepository.deleteById(id);
    }

}
