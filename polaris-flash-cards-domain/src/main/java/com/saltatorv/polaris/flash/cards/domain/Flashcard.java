package com.saltatorv.polaris.flash.cards.domain;

public class Flashcard {
    private final String blueprintId;
    private final String question;
    private final String definition;
    private Answer answer;

    Flashcard(String blueprintId, String question, String definition) {
        this(blueprintId, question, definition, Answer.NOT_ANSWERED);
    }

    private Flashcard(String blueprintId, String question, String definition, Answer answer) {
        this.blueprintId = blueprintId;
        this.question = question;
        this.definition = definition;
        this.answer = answer;
    }

    public static Flashcard restore(FlashcardSnapshot snapshot) {
        return new Flashcard(snapshot.getFlashcardBlueprintId(),
                snapshot.getQuestion(),
                snapshot.getDefinition(),
                Answer.valueOf(snapshot.getAnswer()));
    }

    FlashcardSnapshot generateSnapshot() {
        return new FlashcardSnapshot(blueprintId, question, definition, answer);
    }

    void markAsSuccess() {
        this.answer = Answer.CORRECT;
    }

    void markAsFailure() {
        this.answer = Answer.INCORRECT;
    }

    public void markAsReviewed() {
        this.answer = Answer.REVIEWED;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean isCorrectAnswer() {
        return this.answer.isCorrect();
    }

    public boolean isIncorrectAnswer() {
        return this.answer == Answer.INCORRECT;
    }

    public boolean isNotAnswered() {
        return this.answer == Answer.NOT_ANSWERED;
    }

    public boolean isReviewed() {
        return this.answer == Answer.REVIEWED;
    }

    public String getBlueprintId() {
        return blueprintId;
    }
}
