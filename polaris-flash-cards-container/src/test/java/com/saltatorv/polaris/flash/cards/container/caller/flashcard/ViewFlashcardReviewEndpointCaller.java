package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

import com.saltatorv.polaris.flash.cards.application.query.review.dto.FlashcardReviewDataDto;

public interface ViewFlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller lifecycle();

    FlashcardReviewDataDto getReview();
    }

