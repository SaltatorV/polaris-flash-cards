package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.domain.Flashcard;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NextFlashcardFromReviewUseCase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    public NextFlashcardFromReviewUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public FlashcardDto nextFlashcard(FlashcardReviewId id) {
        Optional<FlashcardReviewSnapshot> reviewSnapshot = flashcardReviewRepository.findById(id);

        if (reviewSnapshot.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }
        FlashcardReview review = FlashcardReview.restore(reviewSnapshot.get());

        Flashcard nextFlashcard = review.next();

        FlashcardDto dto = new FlashcardDto(nextFlashcard.getQuestion(),
                nextFlashcard.getDefinition());

        System.out.println(dto.getQuestion() + " - " + dto.getDefinition());

        flashcardReviewRepository.save(review.generateSnapshot());

        return dto;
    }
}
