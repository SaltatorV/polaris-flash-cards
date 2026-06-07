package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardContentCannotBeEmptyDomainException;

public class FlashcardContent {

    private final String question;
    private final String answer;

    public FlashcardContent(String question, String answer) {
        if (question == null || question.isBlank() ||
                answer == null || answer.isBlank()) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FlashcardContent that = (FlashcardContent) o;
        return question.equals(that.question) && answer.equals(that.answer);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answer.hashCode();
        return result;
    }
}
