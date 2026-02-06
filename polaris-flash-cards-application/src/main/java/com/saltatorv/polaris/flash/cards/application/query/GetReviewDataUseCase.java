package com.saltatorv.polaris.flash.cards.application.query;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.Optional;

public class GetReviewDataUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    GetReviewDataUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public FlashcardReviewDataSnapshot getReviewData(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);
        return new FlashcardReviewDataSnapshot(id, review.getCorrectAnswers(),
                review.getIncorrectAnswers(), review.flashcardCount(),
                review.getStartDate(), review.getFinishDate());
    }


    private FlashcardReview getReview(FlashcardReviewId id) {
        Optional<FlashcardReview> review = flashcardReviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }
        return review.get();
    }
}
