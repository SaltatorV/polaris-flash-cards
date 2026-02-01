package com.saltatorv.polaris.flash.cards.domain;

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

    public FlashcardMetadata getMetadata() {
        return metadata;
    }
}
