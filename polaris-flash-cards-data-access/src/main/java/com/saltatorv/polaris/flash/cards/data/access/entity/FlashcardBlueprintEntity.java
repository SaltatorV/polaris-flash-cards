package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FlashcardBlueprintEntity {
    @Id
    private String id;
    private String question;
    private String definition;
    private String tags;
    private String language;
    private String source;
    private String author;

    public FlashcardBlueprintEntity() {
    }

    public FlashcardBlueprintEntity(String id, String question, String definition, String tags, String language, String source, String author) {
        this.id = id;
        this.question = question;
        this.definition = definition;
        this.tags = tags;
        this.language = language;
        this.source = source;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }

    public String getTags() {
        return tags;
    }

    public String getLanguage() {
        return language;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }
}
