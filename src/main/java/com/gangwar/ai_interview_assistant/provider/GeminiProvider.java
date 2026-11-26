package com.gangwar.ai_interview_assistant.provider;

import com.gangwar.ai_interview_assistant.client.GeminiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("geminiProvider")
@ConditionalOnProperty(
        name = "app.ai.provider",
        havingValue = "gemini",
        matchIfMissing = true
)
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
