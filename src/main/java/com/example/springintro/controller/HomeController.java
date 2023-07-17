package com.example.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // wecome page보다 우선순위를 가지기에 먼저 매핑됨.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
