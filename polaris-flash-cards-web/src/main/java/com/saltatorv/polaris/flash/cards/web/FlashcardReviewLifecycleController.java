package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.review.lifecycle.FlashcardReviewLifecycleFacade;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardReviewLifecycleController extends BaseController {
    public final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/review";
    public final static String FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/generate/random";
    public final static String FLASHCARD_REVIEW_DELETE_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}/delete";

    private final FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade;

    public FlashcardReviewLifecycleController(FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade) {
        this.flashcardReviewLifecycleFacade = flashcardReviewLifecycleFacade;
    }

    @PostMapping(FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT)
    public String generateRandomReview(@RequestBody int size) {
        FlashcardReviewId id = flashcardReviewLifecycleFacade.generateRandomReview(size);
        return id.getId();
    }

    @PostMapping(FLASHCARD_REVIEW_DELETE_ENDPOINT)
    public void deleteReview(@RequestBody String id) {
        flashcardReviewLifecycleFacade.deleteReview(new FlashcardReviewId(id));
    }
}
