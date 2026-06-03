package com.saltatorv.polaris.flash.cards.application.command.blueprint.dto;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintDto {
    private String categoryId;
    private String source;
    private Set<String> tags;
    private List<FlashcardLocalizationDto> localizations;

    public FlashcardBlueprintDto(String categoryId, String source,
                                 Set<String> tags, List<FlashcardLocalizationDto> localizations) {
        this.categoryId = categoryId;
        this.source = source;
        this.tags = tags;
        this.localizations = localizations;
    }

    public FlashcardBlueprintDto() {
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

    public List<FlashcardLocalizationDto> getLocalizations() {
        return localizations;
    }
}
