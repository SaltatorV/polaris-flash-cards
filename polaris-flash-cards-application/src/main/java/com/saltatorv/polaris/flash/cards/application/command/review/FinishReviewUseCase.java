package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinishReviewUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    public FinishReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public void finishReview(FlashcardReviewId id) {
        Optional<FlashcardReviewSnapshot> reviewSnapshot = flashcardReviewRepository.findById(id);

        if (reviewSnapshot.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        FlashcardReview review = FlashcardReview.restore(reviewSnapshot.get());

        review.finish();

        flashcardReviewRepository.save(review.generateSnapshot());
    }
}
