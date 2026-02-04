package com.saltatorv.polaris.flash.cards.domain.shared;

import java.util.UUID;

public class FlashcardBlueprintId extends Id {

    private FlashcardBlueprintId(UUID id) {
        super(id);
    }

    public static FlashcardBlueprintId generate() {
        return new FlashcardBlueprintId(UUID.randomUUID());
    }
}
