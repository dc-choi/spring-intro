package com.example.springintro;

import com.example.springintro.repository.JdbcMemberRepository;
import com.example.springintro.repository.JdbcTemplateMemberRepository;
import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import com.example.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 컴포넌트 스캔을 제외하고 직접 Bean을 등록하는 방법이다.
@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    /**
     * 개방-폐쇄 원칙(OCP, Open-Closed Principle) 확장에는 열려있고, 수정, 변경에는 닫혀있다.
     * 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
     * 회원을 등록하고 DB에 결과가 잘 입력되는지 확인하자.
     * 데이터를 DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장된다.
     *
     * @return
     */
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
