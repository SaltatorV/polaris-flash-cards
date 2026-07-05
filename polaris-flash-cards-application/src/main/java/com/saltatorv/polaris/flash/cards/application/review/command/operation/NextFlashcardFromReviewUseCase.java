package com.saltatorv.polaris.flash.cards.application.review.command.operation;

import com.saltatorv.polaris.flash.cards.application.review.command.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.application.review.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.Flashcard;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.review.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.review.NoMoreQuestionsInFlashcardReviewDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.*;

@Service
class NextFlashcardFromReviewUseCase extends FlashcardReviewUseCaseBase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    NextFlashcardFromReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    FlashcardDto nextFlashcard(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        Flashcard nextFlashcard;
        try {
            nextFlashcard = review.next();
            FlashcardDto dto = new FlashcardDto(nextFlashcard.getQuestion(),
                    nextFlashcard.getDefinition());

            flashcardReviewRepository.save(review.generateSnapshot());

            return dto;
        } catch (FlashcardReviewNotStartedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_STARTED, ex.getMessage());
        } catch (FlashcardReviewAlreadyFinishedDomainException ex) {
            throw new ApplicationException(REVIEW_ALREADY_FINISHED, ex.getMessage());
        } catch (NoMoreQuestionsInFlashcardReviewDomainException ex) {
            throw new ApplicationException(NO_MORE_QUESTIONS_IN_REVIEW, ex.getMessage());
        }
    }
}
