package com.saltatorv.polaris.flash.cards.domain;

public class FlashcardSnapshot {
    private final String flashcardBlueprintId;
    private final String question;
    private final String definition;
    private final String answer;

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition, Answer answer) {
        this(flashcardBlueprintId, question, definition, answer.name());
    }

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition, String answer) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.question = question;
        this.definition = definition;
        this.answer = answer;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }
}
