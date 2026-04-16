package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashcardReviewEntity {
    @Id
    private String id;
    private Long startDate;
    private Long finishDate;
    @OrderBy("position ASC")
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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Long finishDate) {
        this.finishDate = finishDate;
    }

    public List<FlashcardRevisionEntity> getFlashcardRevisions() {
        return flashcardRevisions;
    }

}
