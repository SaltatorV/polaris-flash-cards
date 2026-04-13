package com.saltatorv.polaris.flash.cards.application.query.review.view;

import com.saltatorv.polaris.flash.cards.application.query.review.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
class GetReviewDataUseCase extends FlashcardReviewUseCaseBase {
    private final FlashcardReviewRepository flashcardReviewRepository;

    GetReviewDataUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    FlashcardReviewDataDto getReviewData(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);
        return new FlashcardReviewDataDto(id.getId(), review.getCorrectAnswers(),
                review.getIncorrectAnswers(), review.flashcardCount(),
                review.getStartDate(), review.getFinishDate());
    }
}
