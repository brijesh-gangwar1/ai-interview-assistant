package com.gangwar.ai_interview_assistant.controller;

import com.gangwar.ai_interview_assistant.dto.AskRequest;
import com.gangwar.ai_interview_assistant.dto.AskResponse;
import com.gangwar.ai_interview_assistant.service.GeminiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

    private final GeminiService geminiService;

    public AiController(GeminiService geminiService) {
        this.geminiService =  geminiService;
    }

    @PostMapping("/ask")
    public AskResponse ask(@Valid @RequestBody AskRequest request) {
        return geminiService.ask(request.question());
    }
}