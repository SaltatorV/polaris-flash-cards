package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinishReviewUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    public FinishReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public void finishReview(FlashcardReviewId id) {
        Optional<FlashcardReview> review = flashcardReviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        review.get().finish();
        flashcardReviewRepository.save(review.get());
    }
}
