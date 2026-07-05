package com.saltatorv.polaris.flash.cards.application.review.command.operation;

import com.saltatorv.polaris.flash.cards.application.review.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.review.NoFlashcardReceivedFromReviewDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.NO_FLASHCARD_RECEIVED;

@Service
class AnswerFlashcardUseCase extends FlashcardReviewUseCaseBase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    AnswerFlashcardUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    void markFlashcardAsCorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);


        try {
            review.markFlashcardAsCorrect();
        } catch (NoFlashcardReceivedFromReviewDomainException ex) {
            throw new ApplicationException(NO_FLASHCARD_RECEIVED, ex.getMessage());
        }

        flashcardReviewRepository.save(review.generateSnapshot());
    }

    void markFlashcardAsIncorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        try {
            review.markFlashcardAsIncorrect();
        } catch (NoFlashcardReceivedFromReviewDomainException ex) {
            throw new ApplicationException(NO_FLASHCARD_RECEIVED, ex.getMessage());
        }

        flashcardReviewRepository.save(review.generateSnapshot());
    }


}