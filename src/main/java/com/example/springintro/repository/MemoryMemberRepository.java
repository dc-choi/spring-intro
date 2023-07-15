package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequece = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequece);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Map 값을 스트림(루프)으로 돌려서 매개변수와 같은게 있는지 확인하고 값을 반환한다.
        // findAny()는 스트림에서 가장 먼저 탐색되는 요소를 리턴한다.
        // findFirst()는 조건에 일치하는 요소들 중에 스트림에서 순서가 가장 앞에 있는 요소를 리턴한다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
