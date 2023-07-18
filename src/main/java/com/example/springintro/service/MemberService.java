package com.example.springintro.service;

import com.example.springintro.domain.Member;
import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// @Service
@Transactional
public class MemberService {
    private final MemberRepository repository;

    // @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원의 가입을 막는다.
        validateDuplicateMember(member);

        repository.save(member);
        return member.getId();
    }

    public List<Member> list() {
        return repository.findAll();
    }

    public Optional<Member> get(Long id) {
        return repository.findById(id);
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(data -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
