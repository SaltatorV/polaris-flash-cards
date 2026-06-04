package com.saltatorv.polaris.flash.cards.application.query.blueprint.dto;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintSummaryQueryDto {
    private final String flashcardBlueprintId;
    private final String source;
    private final Set<String> tags;
    private final List<String> availableLocalizations;

    public FlashcardBlueprintSummaryQueryDto(String flashcardBlueprintId, String source,
                                             Set<String> tags, List<String> availableLocalizations) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.source = source;
        this.tags = tags;
        this.availableLocalizations = availableLocalizations;
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

    public List<String> getAvailableLocalizations() {
        return List.copyOf(availableLocalizations);
    }
}
