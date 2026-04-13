package com.saltatorv.polaris.flash.cards.web.controller.query.review;

import com.saltatorv.polaris.flash.cards.application.query.review.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.application.query.review.view.FlashcardReviewViewFacade;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardReviewViewController extends BaseController {
    public final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/review";
    public final static String FLASHCARD_REVIEW_GET_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/{reviewId}";

    private final FlashcardReviewViewFacade flashcardReviewViewFacade;

    public FlashcardReviewViewController(FlashcardReviewViewFacade flashcardReviewViewFacade) {
        this.flashcardReviewViewFacade = flashcardReviewViewFacade;
    }

    @GetMapping(FLASHCARD_REVIEW_GET_ENDPOINT)
    public FlashcardReviewDataDto getFlashcardReview(@PathVariable("reviewId") String reviewId) {
        return flashcardReviewViewFacade.getReviewData(new FlashcardReviewId(reviewId));
    }


}
