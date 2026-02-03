package com.saltatorv.polaris.flash.cards.domain;

@Generated
class FlashcardBlueprint {
    private final String question;
    private final String answer;
    private final FlashcardMetadata metadata;

    FlashcardBlueprint(String question, String answer, FlashcardMetadata metadata) {
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
}
