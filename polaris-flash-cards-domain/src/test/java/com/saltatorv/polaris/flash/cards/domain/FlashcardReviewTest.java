package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.NoMoreQuestionsInFlashcardReviewDomainException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.domain.Answer.*;
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
    public void testShouldThrowExceptionWhenTryBeginFinishedReview() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .create();

        //when
        review.begin();
        review.finish();
        assertThrows(FlashcardReviewAlreadyFinishedDomainException.class, review::begin);

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
        Flashcard question = review.next();

        //then
        assertEquals("Question-1", question.getQuestion());
        assertEquals("Answer-1", question.getDefinition());
    }

    @Test
    public void testShouldGetAppropriateQuestionAccordingToNextMethodCall() {
        //given
        FlashcardReview review = prepareAndBeginReview();

        //when
        review.next();
        Flashcard question = review.next();

        //then
        assertEquals("Question-2", question.getQuestion());
        assertEquals("Answer-2", question.getDefinition());
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

    @Test
    public void testShouldMarkQuestionsAsCorrectOrIncorrect() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .addFlashcard("Question-3", "Answer-3")
                .addFlashcard("Question-4", "Answer-4")
                .addFlashcard("Question-5", "Answer-5")
                .addFlashcard("Question-6", "Answer-6")
                .addFlashcard("Question-7", "Answer-7")
                .create();

        review.begin();

        //when
        incorrectAnswer(review);
        correctAnswer(review);
        incorrectAnswer(review);
        incorrectAnswer(review);
        correctAnswer(review);
        correctAnswer(review);
        correctAnswer(review);

        //then
        assertEquals(4, review.getCorrectAnswers());
        assertEquals(3, review.getIncorrectAnswers());
    }

    @Test
    public void testShouldGenerateSnapshotFromReview() {
        //given
        FlashcardReview review = buildFlashcardReview()
                .addFlashcard("Question-1", "Answer-1")
                .addFlashcard("Question-2", "Answer-2")
                .addFlashcard("Question-3", "Answer-3")
                .create();
        review.begin();
        correctAnswer(review);
        incorrectAnswer(review);
        waitSomeTime();
        review.finish();
        //when

        FlashcardReviewSnapshot snapshot = review.generateSnapshot();

        //then
        assertGeneratedSnapshotIsValid(snapshot, review, List.of(CORRECT, INCORRECT, NOT_ANSWERED));
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

    private void correctAnswer(FlashcardReview review) {
        review.markFlashcardAsCorrect();
        if (review.flashcardCount() > review.getIncorrectAnswers() + review.getCorrectAnswers()) {
            review.next();
        }
    }

    private void incorrectAnswer(FlashcardReview review) {
        review.markFlashcardAsIncorrect();
        if (review.flashcardCount() > review.getIncorrectAnswers() + review.getCorrectAnswers()) {
            review.next();
        }
    }

    private void assertGeneratedSnapshotIsValid(FlashcardReviewSnapshot snapshot, FlashcardReview review, List<Answer> answers) {
        assertEquals(review.getId().getId(), snapshot.getFlashcardReviewId());
        assertEquals(review.getStartDate(), snapshot.getStartDate());
        assertEquals(review.getFinishDate(), snapshot.getFinishDate());

        List<FlashcardSnapshot> flashcardSnapshots = snapshot.getFlashcardSnapshots();

        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i), flashcardSnapshots.get(i).getAnswer());
        }
    }

}
