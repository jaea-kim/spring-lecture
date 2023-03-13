package introTest.introspring.service;

import introTest.introspring.domain.Member;
import introTest.introspring.repository.MemberRepository;
import introTest.introspring.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


class MemberServiceTest {

    MemberRepository memberRepository; // = new MemoryMemberRepository();
    MemberService memberService;// = new MemberService();

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        Member member = new Member();
        member.setName("dewey");

        memberService.join(member);
        assertThat(memberService.findOne(member.getId()).get()).isEqualTo(member);

    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("dewey");
        Member member2 = new Member();
        member2.setName("dewey2");

        memberService.join(member1);
        memberService.join(member2);

        List<Member> members = memberService.findMembers();

        assertThat(members.size()).isEqualTo(2);
    }

}