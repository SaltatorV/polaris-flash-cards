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

    public FlashcardRevisionEntity() {
    }

    public FlashcardRevisionEntity(FlashcardReviewEntity flashcardReview, FlashcardBlueprintEntity flashcardBlueprint, String status) {
        this.flashcardReview = flashcardReview;
        this.flashcardBlueprint = flashcardBlueprint;
        this.status = status;
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

}

