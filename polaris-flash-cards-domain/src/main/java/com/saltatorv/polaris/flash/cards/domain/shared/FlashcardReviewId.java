package com.saltatorv.polaris.flash.cards.domain.shared;

import com.saltatorv.polaris.flash.cards.domain.Generated;

@Generated
public class FlashcardReviewId extends Id {
    public FlashcardReviewId(String id) {
        super(id);
    }

    public FlashcardReviewId() {
        super();
    }

    public static FlashcardReviewId generate() {
        return new FlashcardReviewId();
    }
}
