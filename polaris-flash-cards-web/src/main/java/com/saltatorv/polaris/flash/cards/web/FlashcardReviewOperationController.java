package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.application.command.review.operation.FlashcardReviewOperationFacade;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FlashcardReviewOperationController extends BaseController {
    public final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/review";
    public final static String FLASHCARD_REVIEW_BEGIN_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/begin";
    public final static String FLASHCARD_REVIEW_FINISH_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/finish";
    public final static String FLASHCARD_REVIEW_DRAW_NEXT_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/flashcard/drawNext";
    public final static String FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/flashcard/markAsCorrect";
    public final static String FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/flashcard/markAsIncorrect";

    private final FlashcardReviewOperationFacade flashcardReviewOperationFacade;

    public FlashcardReviewOperationController(FlashcardReviewOperationFacade flashcardReviewOperationFacade) {
        this.flashcardReviewOperationFacade = flashcardReviewOperationFacade;
    }

    @PostMapping(FLASHCARD_REVIEW_BEGIN_ENDPOINT)
    public void beginReview(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.beginReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping(FLASHCARD_REVIEW_FINISH_ENDPOINT)
    public void finishReview(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.finishReview(new FlashcardReviewId(reviewId));
    }

    @PostMapping(FLASHCARD_REVIEW_DRAW_NEXT_ENDPOINT)
    public FlashcardDto drawNextFlashcard(@PathVariable("reviewId") String reviewId) {
        return flashcardReviewOperationFacade.nextFlashcard(new FlashcardReviewId(reviewId));
    }

    @PostMapping(FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
    public void markFlashcardAsCorrect(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.markFlashcardAsCorrect(new FlashcardReviewId(reviewId));
    }

    @PostMapping(FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
    public void markFlashcardAsIncorrect(@PathVariable("reviewId") String reviewId) {
        flashcardReviewOperationFacade.markFlashcardAsIncorrect(new FlashcardReviewId(reviewId));
    }
}
