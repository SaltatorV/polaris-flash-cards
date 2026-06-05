package com.saltatorv.polaris.flash.cards.application.query.blueprint.dto;

import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintQueryDto {
    private final String flashcardBlueprintId;
    private final String source;
    private final Set<String> tags;
    private final List<FlashcardLocalizationQueryDto> flashcardLocalizations;

    public FlashcardBlueprintQueryDto(String flashcardBlueprintId, String source,
                                      Set<String> tags, List<FlashcardLocalizationQueryDto> flashcardLocalizations) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.source = source;
        this.tags = tags;
        this.flashcardLocalizations = flashcardLocalizations;
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
