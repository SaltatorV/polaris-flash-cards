package com.saltatorv.polaris.flash.cards.application.command.dto;

public class FlashcardDto {
    private final String question;
    private final String definition;

    public FlashcardDto(String question, String definition) {
        this.question = question;
        this.definition = definition;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }
}
