package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashcardReviewEntity {
    @Id
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    @OneToMany(mappedBy = "flashcardReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashcardRevisionEntity> flashcardRevisions = new ArrayList<>();

    public FlashcardReviewEntity() {
    }

    public FlashcardReviewEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public List<FlashcardRevisionEntity> getFlashcardRevisions() {
        return flashcardRevisions;
    }

}
