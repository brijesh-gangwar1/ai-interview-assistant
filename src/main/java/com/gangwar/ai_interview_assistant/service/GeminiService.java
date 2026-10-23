package com.gangwar.ai_interview_assistant.service;

import com.gangwar.ai_interview_assistant.client.GeminiClient;
import com.gangwar.ai_interview_assistant.dto.AskResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final GeminiClient geminiClient;

    public GeminiService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public AskResponse ask(String question) {
        String answer = geminiClient.askGemini(question);
        return new AskResponse(answer);
    }
}