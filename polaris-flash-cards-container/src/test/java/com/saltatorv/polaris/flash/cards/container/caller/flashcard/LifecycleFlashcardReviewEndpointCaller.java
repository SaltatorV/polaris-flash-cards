package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

import java.util.List;

public interface LifecycleFlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller begin();

    LifecycleFlashcardReviewEndpointCaller finish();

    LifecycleFlashcardReviewEndpointCaller drawNext(List<String> drewQuestions);

    LifecycleFlashcardReviewEndpointCaller markAsIncorrect();

    LifecycleFlashcardReviewEndpointCaller markAsCorrect();

    ViewFlashcardReviewEndpointCaller view();
}
