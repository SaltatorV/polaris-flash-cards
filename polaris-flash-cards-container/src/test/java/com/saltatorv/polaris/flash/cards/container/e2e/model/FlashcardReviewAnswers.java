package com.saltatorv.polaris.flash.cards.container.e2e.model;

import java.util.Arrays;
import java.util.List;

public record FlashcardReviewAnswers(List<Boolean> answers) {

    public static FlashcardReviewAnswers of(Boolean... answers) {
        return new FlashcardReviewAnswers(Arrays.asList(answers));
    }
}
