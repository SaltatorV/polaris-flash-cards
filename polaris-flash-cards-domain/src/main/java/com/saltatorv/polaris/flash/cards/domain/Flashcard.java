package com.saltatorv.polaris.flash.cards.domain;

class Flashcard {
    private final String question;
    private final String definition;
    private Answer answer;

    Flashcard(String question, String definition) {
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

    String getQuestion() {
        return question;
    }

    String getDefinition() {
        return definition;
    }

    boolean isSuccessfulAnswer() {
        return this.answer.isSuccessfulAnswer();
    }
}
