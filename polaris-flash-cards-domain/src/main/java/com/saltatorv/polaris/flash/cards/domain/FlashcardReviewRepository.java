package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.Optional;

public interface FlashcardReviewRepository {
    Optional<FlashcardReview> findById(FlashcardReviewId id);

    FlashcardReview save(FlashcardReview flashcardReview);
}
