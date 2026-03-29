package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.review.lifecycle.FlashcardReviewLifecycleFacade;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flashcard/review")
class FlashcardReviewLifecycleController {
    private final FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade;

    public FlashcardReviewLifecycleController(FlashcardReviewLifecycleFacade flashcardReviewLifecycleFacade) {
        this.flashcardReviewLifecycleFacade = flashcardReviewLifecycleFacade;
    }

    @PostMapping("/generate/random")
    public String generateRandomReview(@RequestBody int size) {
        FlashcardReviewId id = flashcardReviewLifecycleFacade.generateRandomReview(size);
        return id.getId();
    }

    @PostMapping("/{reviewId}/delete")
    public void deleteReview(@RequestBody String id) {
        flashcardReviewLifecycleFacade.deleteReview(new FlashcardReviewId(id));
    }
}
