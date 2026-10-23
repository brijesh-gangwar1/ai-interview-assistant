package com.gangwar.ai_interview_assistant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.gemini")
public record GeminiProperties(
        String apiKey,
        String model,
        Duration connectTimeout,
        Duration readTimeout
) {
}