package com.saltatorv.polaris.flash.cards.application.query;

import com.saltatorv.polaris.flash.cards.application.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetReviewDataUseCase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    GetReviewDataUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public FlashcardReviewDataDto getReviewData(FlashcardReviewId id) {
        FlashcardReview review = getReview(id);
        return new FlashcardReviewDataDto(id, review.getCorrectAnswers(),
                review.getIncorrectAnswers(), review.flashcardCount(),
                review.getStartDate(), review.getFinishDate());
    }


    private FlashcardReview getReview(FlashcardReviewId id) {
        Optional<FlashcardReviewSnapshot> reviewSnapshot = flashcardReviewRepository.findById(id);
        if (reviewSnapshot.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }
        return FlashcardReview.restore(reviewSnapshot.get());
    }
}
