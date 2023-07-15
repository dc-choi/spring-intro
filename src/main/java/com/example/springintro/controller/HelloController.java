package com.example.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        // viewResolver가 templates 폴더에서 view를 찾아서 처리한다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        // viewResolver가 templates 폴더에서 view를 찾아서 처리한다.
        return "hello-template";
    }

    @GetMapping("hello-string")
    /**
     * viewResolver 대신 HttpMessageConverter가 동작
     * 기본 문자처리: StringHttpMessageConverter
     * 기본 객체처리: MappingJackson2HttpMessageConverter
     *
     * 객체로 처리하게 되면 기본적으로는 MappingJackson2HttpMessageConverter를 통해 JSON으로 변환된다고 함.
     *
     * 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 HttpMessageConverter가 선택된다.
     */
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
