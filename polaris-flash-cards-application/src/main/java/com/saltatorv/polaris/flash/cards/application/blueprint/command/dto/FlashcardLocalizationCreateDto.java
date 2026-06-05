package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import java.util.Locale;

public class FlashcardLocalizationCreateDto {
    private Locale locale;
    private String question;
    private String answer;

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
