package introTest.introspring.repository;

import introTest.introspring.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @Test
    public void save() {
        Member member = new Member();
        member.setName("dewey");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member dewey = new Member();
        dewey.setName("dewey");

        repository.save(dewey);

        Member result = repository.findByName("dewey").get();

        assertThat(result).isEqualTo(dewey);
    }

}