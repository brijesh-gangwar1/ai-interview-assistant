package com.gangwar.ai_interview_assistant;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai/")
public class AiController {

    @GetMapping
    public String index() {
        return "Hello World";
    }
}
