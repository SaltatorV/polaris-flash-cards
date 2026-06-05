package com.saltatorv.polaris.flash.cards.application.query.blueprint.dto;

import java.util.Locale;

public class FlashcardLocalizationQueryDto {
    private final Locale locale;
    private final String question;
    private final String answer;

    public FlashcardLocalizationQueryDto(Locale locale, String question, String answer) {
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
