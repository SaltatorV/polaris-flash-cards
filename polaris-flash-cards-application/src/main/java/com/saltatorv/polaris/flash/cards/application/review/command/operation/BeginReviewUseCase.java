package com.saltatorv.polaris.flash.cards.application.review.command.operation;

import com.saltatorv.polaris.flash.cards.application.review.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewAlreadyStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.REVIEW_ALREADY_FINISHED;
import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.REVIEW_ALREADY_STARTED;

@Service
class BeginReviewUseCase extends FlashcardReviewUseCaseBase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    BeginReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    void beginReview(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);


        try {
            review.begin();
        } catch (FlashcardReviewAlreadyFinishedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_FINISHED, ex.getMessage());
        } catch (FlashcardReviewAlreadyStartedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_STARTED, ex.getMessage());
        }

        flashcardReviewRepository.save(review.generateSnapshot());
    }
}
