package com.saltatorv.polaris.flash.cards.application.command.review.operation;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.Flashcard;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
class NextFlashcardFromReviewUseCase extends FlashcardReviewUseCaseBase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    NextFlashcardFromReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    FlashcardDto nextFlashcard(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        Flashcard nextFlashcard = review.next();

        FlashcardDto dto = new FlashcardDto(nextFlashcard.getQuestion(),
                nextFlashcard.getDefinition());

        flashcardReviewRepository.save(review.generateSnapshot());

        return dto;
    }
}
