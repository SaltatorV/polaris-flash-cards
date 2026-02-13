package com.saltatorv.polaris.flash.cards.domain.shared;

import java.util.UUID;

public class FlashcardBlueprintId extends Id<UUID> {

    public FlashcardBlueprintId(UUID id) {
        super(id);
    }

    private FlashcardBlueprintId(String id) {
        this(UUID.fromString(id));
    }

    public static FlashcardBlueprintId generate() {
        return new FlashcardBlueprintId(UUID.randomUUID());
    }
}
