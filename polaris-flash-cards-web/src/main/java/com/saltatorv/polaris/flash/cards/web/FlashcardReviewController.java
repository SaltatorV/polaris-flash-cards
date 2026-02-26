package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.review.*;
import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flashcard/review")
class FlashcardReviewController {
    private final GenerateReviewUseCase generateReviewUseCase;
    private final BeginReviewUseCase beginReviewUseCase;
    private final FinishReviewUseCase finishReviewUseCase;
    private final NextFlashcardFromReviewUseCase nextFlashcardFromReviewUseCase;
    private final AnswerFlashcardUseCase answerFlashcardUseCase;

    public FlashcardReviewController(GenerateReviewUseCase generateReviewUseCase, BeginReviewUseCase beginReviewUseCase, FinishReviewUseCase finishReviewUseCase, NextFlashcardFromReviewUseCase nextFlashcardFromReviewUseCase, AnswerFlashcardUseCase answerFlashcardUseCase) {
        this.generateReviewUseCase = generateReviewUseCase;
        this.beginReviewUseCase = beginReviewUseCase;
        this.finishReviewUseCase = finishReviewUseCase;
        this.nextFlashcardFromReviewUseCase = nextFlashcardFromReviewUseCase;
        this.answerFlashcardUseCase = answerFlashcardUseCase;
    }

    @PostMapping("/generate/random")
    public String generateRandomReview(@RequestBody int size) {
        FlashcardReviewId id = generateReviewUseCase.generateRandomReview(size);
        return id.getId();
    }

    @PostMapping("/{reviewId}/begin")
    public void beginReview(@PathVariable("reviewId") String reviewId) {
        beginReviewUseCase.beginReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/finish")
    public void finishReview(@PathVariable("reviewId") String reviewId) {
        finishReviewUseCase.finishReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/drawNext")
    public FlashcardDto drawNextFlashcard(@PathVariable("reviewId") String reviewId) {
        return nextFlashcardFromReviewUseCase.nextFlashcard(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/markAsCorrect")
    public void markFlashcardAsCorrect(@PathVariable("reviewId") String reviewId) {
        answerFlashcardUseCase.markFlashcardAsCorrect(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/markAsIncorrect")
    public void markFlashcardAsIncorrect(@PathVariable("reviewId") String reviewId) {
        answerFlashcardUseCase.markFlashcardAsIncorrect(new FlashcardReviewId(reviewId));
    }
}
