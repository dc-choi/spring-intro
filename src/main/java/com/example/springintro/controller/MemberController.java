package com.example.springintro.controller;

import com.example.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService service;

    // @Controller, @Service, @Repository 같은 어노테이션으로 스프링 컨테이너에 Bean으로 등록함.
    // @Autowired는 그 Bean을 찾는 컴포넌트 스캔을 하도록 돕는다.
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }
}
