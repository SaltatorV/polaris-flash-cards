package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

@Entity
public class FlashcardRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_review_id")
    private FlashcardReviewEntity flashcardReview;
    private String flashcardBlueprintId;
    private String status;

    public FlashcardRevisionEntity() {
    }

    public FlashcardRevisionEntity(FlashcardReviewEntity flashcardReview, String flashcardBlueprintId, String status) {
        this.flashcardReview = flashcardReview;
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getStatus() {
        return status;
    }
}
