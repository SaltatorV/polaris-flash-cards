package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FlashcardReviewEntity {
    @Id
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private List<FlashcardRevisionEntity> flashcardRevisions;

    public FlashcardReviewEntity() {
    }

    public FlashcardReviewEntity(String id, LocalDateTime startDate, LocalDateTime finishDate, List<FlashcardRevisionEntity> flashcardRevisions) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.flashcardRevisions = flashcardRevisions;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public List<FlashcardRevisionEntity> getFlashcardRevisions() {
        return flashcardRevisions;
    }
}
