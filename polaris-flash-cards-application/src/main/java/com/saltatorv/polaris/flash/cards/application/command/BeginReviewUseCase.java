package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.Optional;

public class BeginReviewUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    BeginReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public void beginReview(FlashcardReviewId id) {
        Optional<FlashcardReview> review = flashcardReviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        review.get().begin();
        flashcardReviewRepository.save(review.get());
    }
}
