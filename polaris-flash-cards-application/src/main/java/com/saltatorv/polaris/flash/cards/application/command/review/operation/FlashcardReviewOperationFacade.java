package com.saltatorv.polaris.flash.cards.application.command.review.operation;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
public class FlashcardReviewOperationFacade {
    private final BeginReviewUseCase beginReviewUseCase;
    private final FinishReviewUseCase finishReviewUseCase;
    private final NextFlashcardFromReviewUseCase nextFlashcardFromReviewUseCase;
    private final AnswerFlashcardUseCase answerFlashcardUseCase;

    public FlashcardReviewOperationFacade(BeginReviewUseCase beginReviewUseCase, FinishReviewUseCase finishReviewUseCase, NextFlashcardFromReviewUseCase nextFlashcardFromReviewUseCase, AnswerFlashcardUseCase answerFlashcardUseCase) {
        this.beginReviewUseCase = beginReviewUseCase;
        this.finishReviewUseCase = finishReviewUseCase;
        this.nextFlashcardFromReviewUseCase = nextFlashcardFromReviewUseCase;
        this.answerFlashcardUseCase = answerFlashcardUseCase;
    }

    public void beginReview(FlashcardReviewId flashcardReviewId) {
        beginReviewUseCase.beginReview(flashcardReviewId);
    }

    public void finishReview(FlashcardReviewId flashcardReviewId) {
        finishReviewUseCase.finishReview(flashcardReviewId);
    }

    public FlashcardDto nextFlashcard(FlashcardReviewId flashcardReviewId) {
        return nextFlashcardFromReviewUseCase.nextFlashcard(flashcardReviewId);
    }

    public void markFlashcardAsCorrect(FlashcardReviewId flashcardReviewId) {
        answerFlashcardUseCase.markFlashcardAsCorrect(flashcardReviewId);
    }

    public void markFlashcardAsIncorrect(FlashcardReviewId flashcardReviewId) {
        answerFlashcardUseCase.markFlashcardAsIncorrect(flashcardReviewId);
    }
}
