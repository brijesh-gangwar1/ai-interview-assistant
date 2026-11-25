package com.gangwar.ai_interview_assistant.provider;

import org.springframework.stereotype.Component;

@Component("mockAiProvider")
public class MockAiProvider implements AiProvider {

    @Override
    public String generateAnswer(String question) {
        return "Mock answer";
    }
}
