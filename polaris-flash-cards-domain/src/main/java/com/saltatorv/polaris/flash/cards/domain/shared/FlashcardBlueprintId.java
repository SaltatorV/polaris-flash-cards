package com.saltatorv.polaris.flash.cards.domain.shared;

public class FlashcardBlueprintId extends Id {

    public FlashcardBlueprintId(String id) {
        super(id);
    }

    private FlashcardBlueprintId() {
        super();
    }

    public static FlashcardBlueprintId generate() {
        return new FlashcardBlueprintId();
    }
}
