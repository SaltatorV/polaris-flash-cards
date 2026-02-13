package com.saltatorv.polaris.flash.cards.domain.shared;

import com.saltatorv.polaris.flash.cards.domain.Generated;

import java.util.UUID;

@Generated
public class FlashcardReviewId extends Id<UUID> {
    public FlashcardReviewId(UUID id) {
        super(id);
    }

    public FlashcardReviewId(String id) {
        this(UUID.fromString(id));
    }

    public static FlashcardReviewId generate() {
        return new FlashcardReviewId(UUID.randomUUID());
    }
}
