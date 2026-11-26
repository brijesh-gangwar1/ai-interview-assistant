package com.gangwar.ai_interview_assistant.provider;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("mockAiProvider")
@ConditionalOnProperty(
        name = "app.ai.provider",
        havingValue = "mock"
)
public class MockAiProvider implements AiProvider {

    @Override
    public String generateAnswer(String question) {
        return "Mock answer";
    }
}
