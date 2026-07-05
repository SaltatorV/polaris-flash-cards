package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FlashcardLocalizationCreateDto {
    @NotNull(message = "Locale cannot be empty")
    private Locale locale;
    @NotEmpty(message = "Question cannot be empty")
    private String question;
    @NotEmpty(message = "Answer cannot be empty")
    private String answer;

    public FlashcardLocalizationCreateDto() {
    }

    public FlashcardLocalizationCreateDto(Locale locale, String question, String answer) {
        this.locale = locale;
        this.question = question;
        this.answer = answer;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
