package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FlashcardReviewEntity {
    @Id
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    @OneToMany(mappedBy = "flashcardReviewId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashcardRevisionEntity> flashcardRevisions;

    public FlashcardReviewEntity() {
    }

    public FlashcardReviewEntity(String id, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public void setFlashcardRevisions(List<FlashcardRevisionEntity> flashcardRevisions) {
        this.flashcardRevisions = flashcardRevisions;
    }
}
