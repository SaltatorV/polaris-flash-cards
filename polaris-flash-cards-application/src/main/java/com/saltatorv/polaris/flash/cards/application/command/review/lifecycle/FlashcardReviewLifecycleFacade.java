package com.saltatorv.polaris.flash.cards.application.command.review.lifecycle;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
public class FlashcardReviewLifecycleFacade {
    private final GenerateReviewUseCase generateReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;

    public FlashcardReviewLifecycleFacade(GenerateReviewUseCase generateReviewUseCase, DeleteReviewUseCase deleteReviewUseCase) {
        this.generateReviewUseCase = generateReviewUseCase;
        this.deleteReviewUseCase = deleteReviewUseCase;
    }

    public FlashcardReviewId generateRandomReview(int reviewSize) {
        return generateReviewUseCase.generateRandomReview(reviewSize);
    }

    public void deleteReview(FlashcardReviewId id) {
        deleteReviewUseCase.deleteReview(id);
    }
}
