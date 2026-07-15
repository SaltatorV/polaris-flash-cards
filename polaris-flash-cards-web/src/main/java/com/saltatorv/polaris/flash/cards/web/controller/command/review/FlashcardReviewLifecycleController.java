package com.saltatorv.polaris.flash.cards.web.controller.command.review;

import com.saltatorv.polaris.flash.cards.application.review.command.lifecycle.FlashcardReviewLifecycleFacade;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlashcardReviewLifecycleController extends BaseController {
    private final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/review";

    public final static String FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/generate/random";
    public final static String FLASHCARD_REVIEW_DELETE_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/delete";

    private final FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade;

    public FlashcardReviewLifecycleController(FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade) {
        this.flashcardReviewLifecycleFacade = flashcardReviewLifecycleFacade;
    }

    @PostMapping(FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public String generateRandomReview(@Valid @RequestBody int size) {
        FlashcardReviewId id = flashcardReviewLifecycleFacade.generateRandomReview(size);
        return id.getId();
    }

    @DeleteMapping(FLASHCARD_REVIEW_DELETE_ENDPOINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@Valid @PathVariable("reviewId") String reviewId) {
        flashcardReviewLifecycleFacade.deleteReview(new FlashcardReviewId(reviewId));
    }
}
