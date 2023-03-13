package introTest.introspring.repository;

import introTest.introspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    //실무에서는 동시성 문제 때문에 map과 long을 사용하지 않음
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //optional로 감싸면 null을 처릴할 수 있게 도와줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
