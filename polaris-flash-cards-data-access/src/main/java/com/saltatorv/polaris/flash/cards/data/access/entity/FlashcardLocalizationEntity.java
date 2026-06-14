package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

@Entity
public class FlashcardLocalizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "flashcard_blueprint_id")
    private FlashcardBlueprintEntity flashcardBlueprint;
    @Column(columnDefinition = "TEXT")
    private String question;
    @Column(columnDefinition = "TEXT")
    private String definition;
    @Column(length = 3)
    private String language;

    public FlashcardLocalizationEntity() {
    }

    public FlashcardLocalizationEntity(FlashcardBlueprintEntity flashcardBlueprint, String question, String definition, String language) {
        this.flashcardBlueprint = flashcardBlueprint;
        this.question = question;
        this.definition = definition;
        this.language = language;
    }

    public FlashcardBlueprintEntity getFlashcardBlueprint() {
        return flashcardBlueprint;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }

    public String getLanguage() {
        return language;
    }
}
