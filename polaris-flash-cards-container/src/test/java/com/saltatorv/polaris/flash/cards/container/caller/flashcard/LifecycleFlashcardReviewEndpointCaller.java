package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

public interface LifecycleFlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller begin();

    LifecycleFlashcardReviewEndpointCaller finish();

    LifecycleFlashcardReviewEndpointCaller drawNext();

    LifecycleFlashcardReviewEndpointCaller markAsIncorrect();

    LifecycleFlashcardReviewEndpointCaller markAsCorrect();
}
