package com.example.springintro.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP
 * 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)에 대해서 알아보자.
 *
 * AOP가 필요한 경우는 다음과 같다.
 * 1. 공통 관심 사항이거나 핵심 관심 사항일 경우
 * 2. 핵심 비즈니스 로직과 함께 섞여 유지보수가 어려울 때
 * 3. 별도의 공통된 로직으로 만들기 어려운 경우
 * 4. 로직을 변경할 일이 있을 경우 일일히 변경해야 하는 경우
 */
@Component
@Aspect
public class TimeTraceAop {
    // com.example.springintro 패키지 밑에 있는 모든 클래스에 적용한다는 의미이다.
    @Around("execution(* com.example.springintro..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            // AOP를 적용하게 되면 프록시처럼 처리가 된다고 한다.
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + time + "ms");
        }
    }
}
