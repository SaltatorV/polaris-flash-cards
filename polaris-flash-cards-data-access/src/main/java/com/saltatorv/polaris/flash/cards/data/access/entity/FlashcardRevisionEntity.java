package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

@Entity
public class FlashcardRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_review_id")
    private FlashcardReviewEntity flashcardReview;
    @ManyToOne
    @JoinColumn(name = "flashcard_blueprint_id")
    private FlashcardBlueprintEntity flashcardBlueprint;
    private String status;
    private Long startDate;
    private Long finishDate;
    private int position;

    public FlashcardRevisionEntity() {
    }

    public FlashcardRevisionEntity(FlashcardReviewEntity flashcardReview, FlashcardBlueprintEntity flashcardBlueprint,
                                   String status, Long startDate, Long finishDate, int position) {
        this.flashcardReview = flashcardReview;
        this.flashcardBlueprint = flashcardBlueprint;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.position = position;
    }

    public void updateFrom(FlashcardRevisionEntity newOne) {
        this.status = newOne.getStatus();
        this.startDate = newOne.getStartDate();
        this.finishDate = newOne.getFinishDate();
    }

    public Long getId() {
        return id;
    }

    public FlashcardBlueprintEntity getFlashcardBlueprint() {
        return flashcardBlueprint;
    }

    public String getStatus() {
        return status;
    }

    public Long getFinishDate() {
        return finishDate;
    }

    public Long getStartDate() {
        return startDate;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "FlashcardRevisionEntity{" +
                "id=" + id +
                ", flashcardReview=" + flashcardReview.getId() +
                ", flashcardBlueprint=" + flashcardBlueprint.getId() +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}

