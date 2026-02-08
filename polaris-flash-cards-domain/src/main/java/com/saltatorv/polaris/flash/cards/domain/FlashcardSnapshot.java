package com.saltatorv.polaris.flash.cards.domain;

public class FlashcardSnapshot {
    private final String flashcardBlueprintId;
    private final Answer answer;

    public FlashcardSnapshot(String flashcardBlueprintId, Answer answer) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.answer = answer;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public Answer getAnswer() {
        return answer;
    }
}
