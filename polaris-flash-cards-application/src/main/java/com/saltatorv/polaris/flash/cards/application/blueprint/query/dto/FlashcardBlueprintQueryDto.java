package com.saltatorv.polaris.flash.cards.application.blueprint.query.dto;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintQueryDto {
    private String flashcardBlueprintId;
    private String source;
    private Set<String> tags;
    private List<FlashcardLocalizationQueryDto> flashcardLocalizations;

    public FlashcardBlueprintQueryDto(String flashcardBlueprintId, String source,
                                      Set<String> tags, List<FlashcardLocalizationQueryDto> flashcardLocalizations) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.source = source;
        this.tags = tags;
        this.flashcardLocalizations = flashcardLocalizations;
    }

    public FlashcardBlueprintQueryDto() {
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return Set.copyOf(tags);
    }

    public List<FlashcardLocalizationQueryDto> getFlashcardLocalizations() {
        return flashcardLocalizations;
    }
}
