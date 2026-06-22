package com.saltatorv.polaris.flash.cards.web.controller.query.review;

import com.saltatorv.polaris.flash.cards.application.review.query.FlashcardReviewViewFacade;
import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardReviewQueryController extends BaseController {
    private final static String BASE_REVIEW_QUERY_ENDPOINT = "/flashcard/review";

    public final static String FLASHCARD_REVIEW_GET_ENDPOINT = BASE_REVIEW_QUERY_ENDPOINT + "/{reviewId}";

    private final FlashcardReviewViewFacade flashcardReviewViewFacade;

    public FlashcardReviewQueryController(FlashcardReviewViewFacade flashcardReviewViewFacade) {
        this.flashcardReviewViewFacade = flashcardReviewViewFacade;
    }

    @GetMapping(FLASHCARD_REVIEW_GET_ENDPOINT)
    public FlashcardReviewDataDto getFlashcardReview(@PathVariable("reviewId") String reviewId) {
        return flashcardReviewViewFacade.getReviewData(new FlashcardReviewId(reviewId));
    }


}
