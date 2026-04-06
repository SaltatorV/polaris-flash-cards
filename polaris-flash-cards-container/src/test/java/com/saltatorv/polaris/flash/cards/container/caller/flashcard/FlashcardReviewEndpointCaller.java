package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

public interface FlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller generateRandomFlashcardReview(int flashcardCount);

    LifecycleFlashcardReviewEndpointCaller generateFlashcardReview();

    LifecycleFlashcardReviewEndpointCaller generateInvalidFlashcardReview();
}
