package com.saltatorv.polaris.flash.cards.container.caller.review;

import java.util.List;

public interface LifecycleFlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller begin();

    FlashcardReviewEndpointCaller finish();

    LifecycleFlashcardReviewEndpointCaller drawNext(List<String> drewQuestions);

    LifecycleFlashcardReviewEndpointCaller markAsIncorrect();

    LifecycleFlashcardReviewEndpointCaller markAsCorrect();
}
