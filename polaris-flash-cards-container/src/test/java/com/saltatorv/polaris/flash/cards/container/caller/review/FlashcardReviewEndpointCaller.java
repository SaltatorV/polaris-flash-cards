package com.saltatorv.polaris.flash.cards.container.caller.review;

public interface FlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller generateRandomFlashcardReview(int flashcardCount);

    LifecycleFlashcardReviewEndpointCaller generateFlashcardReview();

    LifecycleFlashcardReviewEndpointCaller generateInvalidFlashcardReview();
}
