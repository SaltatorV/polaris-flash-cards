package com.saltatorv.polaris.flash.cards.application.query;

import com.saltatorv.polaris.flash.cards.application.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
public class GetReviewDataUseCase extends FlashcardReviewUseCaseBase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    GetReviewDataUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public FlashcardReviewDataDto getReviewData(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);
        return new FlashcardReviewDataDto(id, review.getCorrectAnswers(),
                review.getIncorrectAnswers(), review.flashcardCount(),
                review.getStartDate(), review.getFinishDate());
    }
}
