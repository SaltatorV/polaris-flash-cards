package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

public class FlashcardLocalizationCreateDto {
    private String locale;
    private String question;
    private String answer;

    public FlashcardLocalizationCreateDto() {
    }

    public FlashcardLocalizationCreateDto(String locale, String question, String answer) {
        this.locale = locale;
        this.question = question;
        this.answer = answer;
    }

    public String getLocale() {
        return locale;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
