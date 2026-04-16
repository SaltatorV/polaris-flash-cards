package com.saltatorv.polaris.flash.cards.domain;

import java.time.LocalDateTime;

public class FlashcardSnapshot {
    private final String flashcardBlueprintId;
    private final String question;
    private final String definition;
    private final String answer;
    private final Long startDate;
    private final Long finishDate;

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition,
                             Answer answer, Long startDate, Long finishDate) {
        this(flashcardBlueprintId, question, definition, answer.name(), startDate, finishDate);
    }

    public FlashcardSnapshot(String flashcardBlueprintId, String question, String definition,
                             String answer, Long startDate, Long finishDate) {
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

    public Long getStartDate() {
        return startDate;
    }

    public Long getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "FlashcardSnapshot{" +
                "flashcardBlueprintId='" + flashcardBlueprintId + '\'' +
                ", question='" + question + '\'' +
                ", definition='" + definition + '\'' +
                ", answer='" + answer + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
