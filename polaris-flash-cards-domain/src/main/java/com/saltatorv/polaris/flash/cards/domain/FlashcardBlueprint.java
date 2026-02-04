package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

@Generated
class FlashcardBlueprint {
    private final FlashcardReviewId reviewId;
    private final String question;
    private final String answer;
    private final FlashcardMetadata metadata;

    FlashcardBlueprint(String question, String answer, FlashcardMetadata metadata) {
        this.reviewId = FlashcardReviewId.generate();
        this.question = question;
        this.answer = answer;
        this.metadata = metadata;
    }

    Flashcard createFlashcard() {
        return new Flashcard(question, answer);
    }

    FlashcardMetadata getMetadata() {
        return metadata;
    }

    FlashcardReviewId getReviewId() {
        return reviewId;
    }
}
