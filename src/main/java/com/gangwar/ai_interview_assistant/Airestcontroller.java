package com.gangwar.ai_interview_assistant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Airestcontroller {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
