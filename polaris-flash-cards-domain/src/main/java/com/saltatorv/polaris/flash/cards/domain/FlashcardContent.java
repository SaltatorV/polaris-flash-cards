package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardContentCannotBeEmptyDomainException;

public class FlashcardContent {

    private final String question;
    private final String answer;

    public FlashcardContent(String question, String answer) {
        if (question == null || answer == null) {
            throw new FlashcardContentCannotBeEmptyDomainException();
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
