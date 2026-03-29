package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.application.command.review.operation.*;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flashcard/review")
class FlashcardReviewOperationController {
   private final FlashcardReviewOperationFacade flashcardReviewOperationFacade;

    public FlashcardReviewOperationController(FlashcardReviewOperationFacade flashcardReviewOperationFacade) {
        this.flashcardReviewOperationFacade = flashcardReviewOperationFacade;
    }

    @PostMapping("/{reviewId}/begin")
    public void beginReview(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.beginReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/finish")
    public void finishReview(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.finishReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/drawNext")
    public FlashcardDto drawNextFlashcard(@PathVariable("reviewId") String reviewId) {
        return flashcardReviewOperationFacade.nextFlashcard(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/markAsCorrect")
    public void markFlashcardAsCorrect(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.markFlashcardAsCorrect(new FlashcardReviewId(reviewId));
    }

    @PostMapping("/{reviewId}/flashcard/markAsIncorrect")
    public void markFlashcardAsIncorrect(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.markFlashcardAsIncorrect(new FlashcardReviewId(reviewId));
    }
}
