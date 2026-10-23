package com.gangwar.ai_interview_assistant.dto;

import jakarta.validation.constraints.NotBlank;

public record AskRequest(

        @NotBlank(message = "Question cannot be blank")
        String question

) {
}