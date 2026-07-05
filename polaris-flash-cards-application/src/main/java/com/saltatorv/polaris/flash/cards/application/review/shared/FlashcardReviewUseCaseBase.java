package com.saltatorv.polaris.flash.cards.application.review.shared;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardReviewSnapshot;

import java.util.Optional;

import static com.saltatorv.polaris.flash.cards.application.review.exception.FlashcardReviewExceptionConfiguration.REVIEW_NOT_FOUND;

public class FlashcardReviewUseCaseBase {

    protected FlashcardReview getReviewFromRepository(FlashcardReviewId id, FlashcardReviewRepository flashcardReviewRepository) {
        Optional<FlashcardReviewSnapshot> reviewSnapshot = flashcardReviewRepository.findById(id);

        if (reviewSnapshot.isEmpty()) {
            throw new ApplicationException(REVIEW_NOT_FOUND, "Review with id: %s do not exists.".formatted(id.getId()));
        }

        return FlashcardReview.restore(reviewSnapshot.get());
    }
}
