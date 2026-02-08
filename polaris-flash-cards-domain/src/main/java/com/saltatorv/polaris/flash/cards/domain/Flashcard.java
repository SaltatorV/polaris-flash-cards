package com.saltatorv.polaris.flash.cards.domain;

public class Flashcard {
    private final String blueprintId;
    private final String question;
    private final String definition;
    private Answer answer;

    Flashcard(String blueprintId, String question, String definition) {
        this.blueprintId = blueprintId;
        this.question = question;
        this.definition = definition;
        this.answer = Answer.NOT_ANSWERED;
    }

    void markAsSuccess() {
        this.answer = Answer.CORRECT;
    }

    void markAsFailure() {
        this.answer = Answer.INCORRECT;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean isSuccessfulAnswer() {
        return this.answer.isSuccessfulAnswer();
    }

    public boolean isIncorrectAnswer() {
        return this.answer == Answer.INCORRECT;
    }

    public boolean isNotAnswered() {
        return this.answer == Answer.NOT_ANSWERED;
    }

    public String getBlueprintId() {
        return blueprintId;
    }
}
