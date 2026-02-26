package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
public class AnswerFlashcardUseCase extends FlashcardReviewUseCaseBase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    public AnswerFlashcardUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    public void markFlashcardAsCorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        review.markFlashcardAsCorrect();

        flashcardReviewRepository.save(review.generateSnapshot());
    }

    public void markFlashcardAsIncorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        review.markFlashcardAsIncorrect();

        flashcardReviewRepository.save(review.generateSnapshot());
    }


}