package com.saltatorv.polaris.flash.cards.domain;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintSnapshot {
    private final String flashcardBlueprintId;
    private final List<FlashcardLocalization> localizations;
    private final String source;
    private final Set<String> tags;

    public FlashcardBlueprintSnapshot(String flashcardBlueprintId, List<FlashcardLocalization> localizations, String source, Set<String> tags) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.localizations = localizations;
        this.source = source;
        this.tags = tags;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public List<FlashcardLocalization> getLocalizations() {
        return localizations;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }
}
