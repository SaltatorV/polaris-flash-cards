package com.saltatorv.polaris.flash.cards.application.blueprint.query.dto;

import java.util.Locale;

public class FlashcardLocalizationQueryDto {
    private Locale locale;
    private String question;
    private String answer;

    public FlashcardLocalizationQueryDto(Locale locale, String question, String answer) {
        this.locale = locale;
        this.question = question;
        this.answer = answer;
    }

    public FlashcardLocalizationQueryDto() {
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
