package com.saltatorv.polaris.flash.cards.application.query;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.time.LocalDateTime;
import java.util.Optional;

class GetReviewDataUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    GetReviewDataUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public int flashcardCount(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);

        return review.flashcardCount();
    }

    public int getCorrectAnswers(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);

        return review.getCorrectAnswers();
    }

    public int getIncorrectAnswers(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);

        return review.getIncorrectAnswers();
    }

    public LocalDateTime getStartDate(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);

        return review.getStartDate();
    }

    public LocalDateTime getFinishDate(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);

        return review.getFinishDate();
    }

    private FlashcardReview getReview(FlashcardReviewId id) {
        Optional<FlashcardReview> review = flashcardReviewRepository.findById(id);
        if(review.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }
        return review.get();
    }
}
