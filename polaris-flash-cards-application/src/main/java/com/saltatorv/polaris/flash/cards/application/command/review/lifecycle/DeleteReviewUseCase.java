package com.saltatorv.polaris.flash.cards.application.command.review.lifecycle;

import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
class DeleteReviewUseCase extends FlashcardReviewUseCaseBase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    DeleteReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    void deleteReview(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);
        flashcardReviewRepository.deleteById(review.getId());
    }

}
