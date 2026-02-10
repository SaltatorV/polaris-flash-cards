package com.saltatorv.polaris.flash.cards.domain;

public class FlashcardSnapshot {
    private final String flashcardBlueprintId;
    private final String answer;

    public FlashcardSnapshot(String flashcardBlueprintId, Answer answer) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.answer = answer.name();
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getAnswer() {
        return answer;
    }
}
