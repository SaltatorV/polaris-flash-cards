package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;

public class FlashcardReviewEndpointCaller implements EndpointCaller {

    public static FlashcardReviewEndpointCaller build() {
        return new FlashcardReviewEndpointCaller();
    }
}
