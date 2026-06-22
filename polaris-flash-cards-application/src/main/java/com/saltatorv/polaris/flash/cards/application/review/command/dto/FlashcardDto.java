package com.saltatorv.polaris.flash.cards.application.review.command.dto;

public class FlashcardDto {
    private String question;
    private String definition;

    public FlashcardDto(String question, String definition) {
        this.question = question;
        this.definition = definition;
    }

    public FlashcardDto() {
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }
}
