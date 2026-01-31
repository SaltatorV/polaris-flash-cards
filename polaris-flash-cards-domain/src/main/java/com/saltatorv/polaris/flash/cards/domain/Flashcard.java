package com.saltatorv.polaris.flash.cards.domain;

class Flashcard {
    private String question;
    private String answer;
    private boolean isSuccess;

    Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    void markAsSuccess() {
        isSuccess = true;
    }

    void markAsFailure() {
        isSuccess = false;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }

    boolean isSuccessfulAnswer() {
        return isSuccess;
    }
}
