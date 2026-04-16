package com.saltatorv.polaris.flash.cards.application.query.review.dto;



public class FlashcardReviewDataDto {
    private String id;
    private int correctAnswers;
    private int incorrectAnswers;
    private int flashcardCount;
    private int notAnsweredCount;
    private Long startDate;
    private  Long finishDate;

    public FlashcardReviewDataDto() {
    }

    public FlashcardReviewDataDto(String id, int correctAnswers,
                                  int incorrectAnswers, int flashcardCount,
                                  Long startDate, Long finishDate) {
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

    public Long getStartDate() {
        return startDate;
    }

    public Long getFinishDate() {
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
