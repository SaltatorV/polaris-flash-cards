package com.saltatorv.polaris.flash.cards.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FlashcardBlueprintSnapshot {
    private final String flashcardBlueprintId;
    private final String categoryId;
    private final List<FlashcardLocalization> localizations;
    private final String source;
    private final Set<String> tags;

    public FlashcardBlueprintSnapshot(String flashcardBlueprintId, String categoryId, List<FlashcardLocalization> localizations, String source, Set<String> tags) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.categoryId = categoryId;
        this.localizations = localizations;
        this.source = source;
        this.tags = tags;
    }

    public String getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }

    public String getCategoryId() {
        return categoryId;
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

    @Override
    @Generated
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FlashcardBlueprintSnapshot snapshot = (FlashcardBlueprintSnapshot) o;
        return Objects.equals(flashcardBlueprintId, snapshot.flashcardBlueprintId);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hashCode(flashcardBlueprintId);
    }
}
