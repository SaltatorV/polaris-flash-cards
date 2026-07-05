package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlashcardLocalizationUpdateDto {
    @NotNull(message = "Locale cannot be empty")
    private Locale locale;
    @NotEmpty(message = "Question cannot be empty")
    @Size(min = 3, max = 1000, message = "Question must be between {min} and {max} characters")
    private String question;
    @NotEmpty(message = "Answer cannot be empty")
    @Size(min = 3, max = 1000, message = "Answer must be between {min} and {max} characters")
    private String answer;

    public FlashcardLocalizationUpdateDto() {
    }

    public FlashcardLocalizationUpdateDto(Locale locale, String question, String answer) {
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
