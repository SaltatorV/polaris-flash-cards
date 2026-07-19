package com.saltatorv.polaris.flash.cards.container.model;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlashcardBlueprintCreationDtoBuilder {
    private String categoryId;
    private String source;
    private Set<String> tags;
    private List<FlashcardLocalizationCreateDto> localizations;

    public FlashcardBlueprintCreationDtoBuilder() {
        tags = new HashSet<>();
        localizations = new ArrayList<>();
    }

    public FlashcardBlueprintCreationDtoBuilder withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public FlashcardBlueprintCreationDtoBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    public FlashcardBlueprintCreationDtoBuilder addTags(String... tags) {
        this.tags.addAll(List.of(tags));
        return this;
    }

    public FlashcardBlueprintCreateDto createDto() {
        return new FlashcardBlueprintCreateDto(categoryId, source, tags, localizations);
    }

    public FlashcardBlueprintLocalizationDtoBuilder defineLocalization() {
        return new FlashcardBlueprintLocalizationDtoBuilder(this);
    }

    void addLocalization(FlashcardLocalizationCreateDto flashcardLocalizationCreateDto) {
        localizations.add(flashcardLocalizationCreateDto);
    }
}
