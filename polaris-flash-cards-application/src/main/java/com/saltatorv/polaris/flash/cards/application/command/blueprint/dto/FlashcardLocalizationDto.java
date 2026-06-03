package com.saltatorv.polaris.flash.cards.application.command.blueprint.dto;

import java.util.Locale;

public class FlashcardLocalizationDto {
    private Locale locale;
    private String question;
    private String answer;

    public FlashcardLocalizationDto(Locale locale, String question, String answer) {
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
