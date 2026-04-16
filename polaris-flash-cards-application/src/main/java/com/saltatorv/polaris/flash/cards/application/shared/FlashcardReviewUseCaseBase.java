package com.saltatorv.polaris.flash.cards.application.shared;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.Optional;

public class FlashcardReviewUseCaseBase {

    protected FlashcardReview getReviewFromRepository(FlashcardReviewId id, FlashcardReviewRepository flashcardReviewRepository) {
        Optional<FlashcardReviewSnapshot> reviewSnapshot = flashcardReviewRepository.findById(id);

        if (reviewSnapshot.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        return FlashcardReview.restore(reviewSnapshot.get());
    }
}
