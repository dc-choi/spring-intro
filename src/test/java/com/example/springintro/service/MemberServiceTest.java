package com.example.springintro.service;

import com.example.springintro.domain.Member;
import com.example.springintro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    // 테스트는 실행순서가 보장되지 않아서 순서에 의존하는 테스트를 짜면 안됨.
    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    // Service 영역을 테스트시에는 비즈니스에 비슷한 부분을 테스트하기에 한글로 테스트를 적어도 상관없음.
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = service.join(member);
        // then
        Optional<Member> result = service.get(saveId);
        assertThat(result.get().getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복회원예외() {
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        service.join(member);
        assertThrows(IllegalStateException.class, () -> service.join(member2));
    }

    @Test
    void list() {
    }

    @Test
    void get() {
    }
}