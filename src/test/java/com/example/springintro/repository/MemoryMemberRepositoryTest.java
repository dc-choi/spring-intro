package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*; // 요즘 사용하는 패키지로 static으로 가져와서 코드를 줄인다.

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 실행순서가 보장되지 않아서 순서에 의존하는 테스트를 짜면 안됨.
    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Optional<Member> result = repository.findById(member.getId());
        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result.get());
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("node.js");
        repository.save(member2);

        Optional<Member> result = repository.findByName("spring");

        assertThat(result.get()).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("node.js");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
