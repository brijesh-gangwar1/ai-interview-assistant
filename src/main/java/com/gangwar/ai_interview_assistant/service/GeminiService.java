package com.gangwar.ai_interview_assistant.service;

import com.gangwar.ai_interview_assistant.dto.AskResponse;
import com.gangwar.ai_interview_assistant.provider.AiProvider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private static final Logger log = LoggerFactory.getLogger(GeminiService.class);

    private final AiProvider aiProvider;

    public GeminiService(
            @Qualifier("geminiProvider") AiProvider aiProvider) {
        this.aiProvider = aiProvider;
    }

    public AskResponse ask(String question) {
        String answer = aiProvider.generateAnswer(question);
        return new AskResponse(answer);
    }

    @PostConstruct
    public void initialize() {
        log.info("GeminiService initialized with provider: {}",
                aiProvider.getClass().getSimpleName());
    }

    @PreDestroy
    public void cleanup() {
        log.info("GeminiService is shutting down");
    }
}