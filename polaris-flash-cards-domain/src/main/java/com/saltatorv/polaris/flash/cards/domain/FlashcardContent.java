package com.saltatorv.polaris.flash.cards.domain;

public class FlashcardContent {

    private final String question;
    private final String answer;

    public FlashcardContent(String question, String answer) {
        if (question == null || answer == null) {
            throw new IllegalArgumentException("Question and answer must be not null");
        }

        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
