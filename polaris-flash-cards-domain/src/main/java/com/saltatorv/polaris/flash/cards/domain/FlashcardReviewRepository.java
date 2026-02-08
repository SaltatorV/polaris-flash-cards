package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.Optional;

public interface FlashcardReviewRepository {
    Optional<FlashcardReviewSnapshot> findById(FlashcardReviewId id);

    FlashcardReviewSnapshot save(FlashcardReviewSnapshot flashcardReview);
}
