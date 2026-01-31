package com.saltatorv.polaris.flash.cards.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.saltatorv.polaris.flash.cards.domain.FlashcardReviewBuilder.buildFlashcardReview;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlashcardReviewTest {


    @Test
    public void testShouldCreateFlashcardReview() {
        //given

        //when
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //then
        assertEquals(2, review.flashcardCount());
    }

    @Test
    public void testShouldGenerateStartDateWhenReviewBegin() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //when
        review.begin();

        //then
        assertTrue(review.getStartDate().isBefore(getCurrentDate()));
    }

    private LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

}
