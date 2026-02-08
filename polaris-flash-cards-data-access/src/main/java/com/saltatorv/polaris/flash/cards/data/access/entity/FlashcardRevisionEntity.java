package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FlashcardRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flashcardReviewId;
    private String flashcardBlueprintId;
    private String status;

    public FlashcardRevisionEntity() {
    }

    public FlashcardRevisionEntity(int id, String flashcardReviewId, String flashcardBlueprintId, String status) {
        this.id = id;
        this.flashcardReviewId = flashcardReviewId;
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFlashcardReviewId() {
        return flashcardReviewId;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getStatus() {
        return status;
    }
}
