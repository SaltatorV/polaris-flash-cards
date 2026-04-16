package com.saltatorv.polaris.flash.cards.application.command.review.operation;

import com.saltatorv.polaris.flash.cards.application.shared.FlashcardReviewUseCaseBase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

@Service
class AnswerFlashcardUseCase extends FlashcardReviewUseCaseBase {

    private final FlashcardReviewRepository flashcardReviewRepository;

    AnswerFlashcardUseCase(FlashcardReviewRepository flashcardReviewRepository) {
        this.flashcardReviewRepository = flashcardReviewRepository;
    }

    void markFlashcardAsCorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        review.markFlashcardAsCorrect();
        System.out.println("Mark as correct");
        flashcardReviewRepository.save(review.generateSnapshot());
    }

    void markFlashcardAsIncorrect(FlashcardReviewId id) {
        FlashcardReview review = getReviewFromRepository(id, flashcardReviewRepository);

        review.markFlashcardAsIncorrect();
        System.out.println("mark as incorrect");
        flashcardReviewRepository.save(review.generateSnapshot());
    }


}