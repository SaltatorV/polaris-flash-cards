package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintCreateDto {
    private String categoryId;
    private String source;
    private Set<String> tags;
    private List<FlashcardLocalizationCreateDto> localizations;

    public FlashcardBlueprintCreateDto(String categoryId, String source,
                                       Set<String> tags, List<FlashcardLocalizationCreateDto> localizations) {
        this.categoryId = categoryId;
        this.source = source;
        this.tags = tags;
        this.localizations = localizations;
    }

    public FlashcardBlueprintCreateDto() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }

    public List<FlashcardLocalizationCreateDto> getLocalizations() {
        return localizations;
    }
}
