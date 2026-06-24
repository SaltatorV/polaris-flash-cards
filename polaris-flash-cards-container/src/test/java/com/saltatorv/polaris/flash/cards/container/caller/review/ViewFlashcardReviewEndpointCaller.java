package com.saltatorv.polaris.flash.cards.container.caller.review;


import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;

public interface ViewFlashcardReviewEndpointCaller {

    LifecycleFlashcardReviewEndpointCaller lifecycle();

    FlashcardReviewDataDto getReview();
    }

