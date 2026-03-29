package com.saltatorv.polaris.flash.cards.domain;

import java.time.LocalDateTime;

public class FlashcardSnapshot {
    private final String flashcardBlueprintId;
    private final String question;
    private final String definition;
    private final String answer;
    private final LocalDateTime startDate;
    private final LocalDateTime finishDate;

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition,
                             Answer answer, LocalDateTime startDate, LocalDateTime finishDate) {
        this(flashcardBlueprintId, question, definition, answer.name(), startDate, finishDate);
    }

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition,
                             String answer, LocalDateTime startDate, LocalDateTime finishDate) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.question = question;
        this.definition = definition;
        this.answer = answer;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }
}
