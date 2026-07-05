package com.saltatorv.polaris.flash.cards.application.review.command.operation;

import com.saltatorv.polaris.flash.cards.application.review.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.REVIEW_ALREADY_FINISHED;
import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.REVIEW_ALREADY_STARTED;

@Service
class FinishReviewUseCase extends FlashcardReviewUseCaseBase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    FinishReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    void finishReview(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        try {
            review.finish();
        } catch (FlashcardReviewNotStartedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_STARTED, ex.getMessage());
        } catch (FlashcardReviewAlreadyFinishedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_FINISHED, ex.getMessage());
        }

        flashcardReviewRepository.save(review.generateSnapshot());
    }
}
