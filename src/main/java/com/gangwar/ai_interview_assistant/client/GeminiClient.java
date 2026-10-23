
package com.gangwar.ai_interview_assistant.client;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import com.gangwar.ai_interview_assistant.config.GeminiProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Component
public class GeminiClient {

    private final WebClient webClient;
    private final JsonMapper objectMapper;
    private final GeminiProperties properties;

    public GeminiClient(
            WebClient geminiWebClient,
            JsonMapper objectMapper,
            GeminiProperties properties) {

        this.webClient = geminiWebClient;
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    private static final Logger log =
            LoggerFactory.getLogger(GeminiClient.class);

    public String askGemini(String question) {

        String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": %s
                        }
                      ]
                    }
                  ]
                }
                """.formatted(
                objectMapper.writeValueAsString(question)
        );

        String response = webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v1beta/models/{model}:generateContent")
                                .queryParam("key", properties.apiKey())
                                .build(properties.model())
                )
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block(properties.readTimeout());

        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode textNode = root
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text");

            if (textNode.isMissingNode()) {
                throw new RuntimeException(
                        "Gemini response does not contain generated text: "
                                + response
                );
            }

            return textNode.asString();

        } catch (Exception e) {
            log.error("Gemini API failed for question: {}", question, e);
            throw new RuntimeException(
                    "Failed to parse Gemini response",
                    e
            );
        }
    }
}
