package com.gangwar.ai_interview_assistant.provider;

import com.gangwar.ai_interview_assistant.client.GeminiClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("geminiProvider")
@Primary
public class GeminiProvider implements AiProvider {

    private final GeminiClient geminiClient;

    public GeminiProvider(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    @Override
    public String generateAnswer(String question) {
        return geminiClient.askGemini(question);
    }
}
