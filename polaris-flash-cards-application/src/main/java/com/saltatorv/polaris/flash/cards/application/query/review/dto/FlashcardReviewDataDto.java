package com.saltatorv.polaris.flash.cards.application.query.review.dto;


import java.time.LocalDateTime;

public class FlashcardReviewDataDto {
    private String id;
    private int correctAnswers;
    private int incorrectAnswers;
    private int flashcardCount;
    private int notAnsweredCount;
    private LocalDateTime startDate;
    private  LocalDateTime finishDate;

    public FlashcardReviewDataDto() {
    }

    public FlashcardReviewDataDto(String id, int correctAnswers,
                                  int incorrectAnswers, int flashcardCount,
                                  LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.flashcardCount = flashcardCount;
        this.notAnsweredCount = flashcardCount - correctAnswers - incorrectAnswers;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getId() {
        return id;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public int getNotAnsweredCount() {
        return notAnsweredCount;
    }

    public int getFlashcardCount() {
        return flashcardCount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "FlashcardReviewDataDto{" +
                "id=" + id +
                ", correctAnswers=" + correctAnswers +
                ", incorrectAnswers=" + incorrectAnswers +
                ", flashcardCount=" + flashcardCount +
                ", notAnsweredCount=" + notAnsweredCount +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
