package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    public FlashcardRevisionEntity() {
    }

    public FlashcardRevisionEntity(FlashcardReviewEntity flashcardReview, FlashcardBlueprintEntity flashcardBlueprint,
                                   String status, LocalDateTime startDate, LocalDateTime finishDate) {
        this.flashcardReview = flashcardReview;
        this.flashcardBlueprint = flashcardBlueprint;
        this.status = status;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}

