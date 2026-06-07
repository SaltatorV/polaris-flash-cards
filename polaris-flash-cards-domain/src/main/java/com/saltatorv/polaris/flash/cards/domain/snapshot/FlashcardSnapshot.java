package com.saltatorv.polaris.flash.cards.domain.snapshot;


import com.saltatorv.polaris.flash.cards.domain.Answer;
import com.saltatorv.polaris.flash.cards.domain.Generated;

@Generated
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FlashcardSnapshot that = (FlashcardSnapshot) o;
        return flashcardBlueprintId.equals(that.flashcardBlueprintId) && question.equals(that.question) && definition.equals(that.definition) && answer.equals(that.answer) && startDate.equals(that.startDate) && finishDate.equals(that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = flashcardBlueprintId.hashCode();
        result = 31 * result + question.hashCode();
        result = 31 * result + definition.hashCode();
        result = 31 * result + answer.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        return result;
    }
}
