package com.saltatorv.polaris.flash.cards.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.saltatorv.polaris.flash.cards.domain.FlashcardReviewBuilder.buildFlashcardReview;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(getNotConfiguredDate(), review.getStartDate());
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

    @Test
    public void testShouldGetFirstQuestion() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        String question = review.next();

        //then
        assertEquals("Question-1", question);
    }

    @Test
    public void testShouldGetAppropriateQuestionAccordingToNextMethodCall() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        review.next();
        String question = review.next();

        //then
        assertEquals("Question-2", question);
    }

    @Test
    public void testShouldNotPassNextQuestionWhenReviewNotStarted() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //when
        assertThrows(RuntimeException.class, review::next);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenTryGetNextQuestionWhenThereIsNoQuestionsLeft() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        review.next();
        review.next();
        assertThrows(RuntimeException.class, review::next);

        //then
    }

    @Test
    public void testShouldMarkFlashcardAsCorrect() {
        //given
        FlashcardReview review = prepareAndBeginReview();
        review.next();

        //when
        review.markFlashcardAsCorrect();
        review.next();

        //then
        assertEquals(1, review.getCorrectAnswers());
    }

    @Test
    public void testShouldNotMarkFlashcardAsAnsweredIfReviewNotProceedNextQuestion() {
        //given
        FlashcardReview review = prepareAndBeginReview();
        review.next();

        //when
        review.markFlashcardAsCorrect();

        //then
        assertEquals(0, review.getCorrectAnswers());
    }

    @Test
    public void testShouldNotMarkFlashcardAsAnsweredIfReviewNotProceedNextQuestion2() {
        //given
        FlashcardReview review = prepareAndBeginReview();
        review.next();

        //when
        review.markFlashcardAsIncorrect();

        //then
        assertEquals(0, review.getIncorrectAnswers());
    }

    @Test
    public void testShouldMarkFlashcardAsFailure() {
        //given
        FlashcardReview review = prepareAndBeginReview();
        review.next();

        //when
        review.markFlashcardAsIncorrect();
        review.next();

        //then
        assertEquals(1, review.getIncorrectAnswers());
    }

    private LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    private LocalDateTime getNotConfiguredDate() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(0),
                ZoneId.systemDefault());
    }

    private FlashcardReview prepareAndBeginReview() {
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();
        review.begin();
        return review;
    }

}
