package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FlashcardBlueprintEntity {
    @Id
    private String id;
    @Column(length = 40)
    private String tags;
    @Column(length = 200)
    private String source;
    @Column(length = 100)
    private String author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @OneToMany(mappedBy = "flashcardBlueprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashcardLocalizationEntity> flashcardLocalizations = new ArrayList<>();

    public FlashcardBlueprintEntity() {
    }

    public FlashcardBlueprintEntity(String id, String tags, String source,
                                    String author, CategoryEntity category,
                                    List<FlashcardLocalizationEntity> flashcardLocalizations) {
        this.id = id;
        this.tags = tags;
        this.source = source;
        this.author = author;
        this.category = category;
        this.flashcardLocalizations = flashcardLocalizations;
    }

    public FlashcardBlueprintEntity(CategoryEntity category, String author, String source, String tags, String id) {
        this.category = category;
        this.author = author;
        this.source = source;
        this.tags = tags;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTags() {
        return tags;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public List<FlashcardLocalizationEntity> getFlashcardLocalizations() {
        return flashcardLocalizations;
    }

    public void setFlashcardLocalizations(List<FlashcardLocalizationEntity> flashcardLocalizations) {
        this.flashcardLocalizations = flashcardLocalizations;
    }
}
