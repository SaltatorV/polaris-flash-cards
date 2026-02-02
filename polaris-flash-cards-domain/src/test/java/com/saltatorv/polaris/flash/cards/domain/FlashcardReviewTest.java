package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.NoMoreQuestionsInFlashcardReviewDomainException;
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
    public void testShouldThrowExceptionWhenTryBeginReviewTwice() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //when
        review.begin();
        assertThrows(FlashcardReviewAlreadyStartedDomainException.class, review::begin);

        //then

    }

    @Test
    public void testShouldGenerateEndDateWhenReviewIsFinished() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        waitSomeTime();
        review.finish();

        //then
        assertTrue(review.getStartDate().isBefore(review.getFinishDate()));
    }

    @Test
    public void testShouldThrowExceptionWhenTryFinishReviewTwice() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        review.finish();
        assertThrows(FlashcardReviewAlreadyFinishedDomainException.class, review::finish);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenTryFinishNotStartedReview() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //when
        assertThrows(FlashcardReviewNotStartedDomainException.class, review::finish);

        //then

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
                .create();

        //when
        assertThrows(FlashcardReviewNotStartedDomainException.class, review::next);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenTryGetNextQuestionWhenThereIsNoQuestionsLeft() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        review.next();
        review.next();
        assertThrows(NoMoreQuestionsInFlashcardReviewDomainException.class, review::next);

        //then
    }

    @Test
    public void testShouldThrowExceptionWhenTryGetNextQuestionWhenReviewFinished() {
        //given
        FlashcardReview review = prepareAndBeginReview();
        review.finish();

        //when
        assertThrows(FlashcardReviewAlreadyFinishedDomainException.class, review::next);

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

    private void waitSomeTime() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
