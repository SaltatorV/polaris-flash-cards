package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

public class FlashcardLocalizationUpdateDto {
    private String locale;
    private String question;
    private String answer;

    public FlashcardLocalizationUpdateDto() {
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
