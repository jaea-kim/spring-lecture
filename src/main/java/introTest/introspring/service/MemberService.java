package introTest.introspring.service;

import introTest.introspring.domain.Member;
import introTest.introspring.repository.MemberRepository;
import introTest.introspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원가입 : 가입 성공 시 ID 반환
    public Long join(Member member) {
        //같은 이름을 가진 멤버는 가입 불가능
        //memberRepository 같은 이름을 가진 멤버가 있는지 확인
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //전체 인원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //한명만 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }
}
