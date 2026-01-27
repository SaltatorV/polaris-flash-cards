package com.saltatorv.polaris.flash.cards.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlashcardReviewTest {

    private FlashcardReview flashcardReview;

    @Test
    public void testShouldCreateFlashcardReview() {
        //given

        //when
        create();

        //then
        assertNotNull(flashcardReview);
    }

    private void create() {
        flashcardReview = new FlashcardReview();
    }
}
