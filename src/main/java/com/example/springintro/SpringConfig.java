package com.example.springintro;

import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import com.example.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 컴포넌트 스캔을 제외하고 직접 Bean을 등록하는 방법이다.
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
