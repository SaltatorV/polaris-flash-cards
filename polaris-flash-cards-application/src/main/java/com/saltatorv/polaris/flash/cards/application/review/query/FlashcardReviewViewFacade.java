package com.saltatorv.polaris.flash.cards.application.review.query;

import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
public class FlashcardReviewViewFacade {
    private final GetReviewDataUseCase getReviewDataUseCase;

    public FlashcardReviewViewFacade(GetReviewDataUseCase getReviewDataUseCase) {
        this.getReviewDataUseCase = getReviewDataUseCase;
    }

    public FlashcardReviewDataDto getReviewData(FlashcardReviewId id) {
        return getReviewDataUseCase.getReviewData(id);
    }
}
