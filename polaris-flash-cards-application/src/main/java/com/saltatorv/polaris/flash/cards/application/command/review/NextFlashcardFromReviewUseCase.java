package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.domain.Flashcard;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NextFlashcardFromReviewUseCase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    public NextFlashcardFromReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public FlashcardDto nextFlashcardDto(FlashcardReviewId id) {
        Optional<FlashcardReview> review = flashcardReviewRepository.findById(id);

        if (review.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        Flashcard nextFlashcard = review.get().next();
        FlashcardDto dto = new FlashcardDto(nextFlashcard.getQuestion(),
                nextFlashcard.getDefinition());
        return dto;
    }
}
