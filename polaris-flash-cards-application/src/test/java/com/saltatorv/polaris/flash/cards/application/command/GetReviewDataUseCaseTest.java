package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.application.query.FlashcardReviewDataSnapshot;
import com.saltatorv.polaris.flash.cards.application.query.GetReviewDataUseCase;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetReviewDataUseCaseTest {

    private FlashcardReview review;
    private FlashcardReviewDataSnapshot reviewData;

    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;

    @InjectMocks
    private GetReviewDataUseCase getReviewDataUseCase;

    @Test
    public void testShouldGetReviewData() {
        //given
        prepareBeginAndFinishReview(2000, 5, 3);

        //when
        getReviewData();

        //then
        assertReviewDataContainsValidDataFromReview(2000, 5, 3);

    }

    @Test
    public void testShouldThrowExceptionWhenReviewNotFound() {
        //given

        //when
        assertThrows(RuntimeException.class, this::getReviewData);

        //then
    }


    private void getReviewData() {
        if (review == null) {
            reviewData = getReviewDataUseCase.getReviewData(FlashcardReviewId.generate());
        } else {
            reviewData = getReviewDataUseCase.getReviewData(review.getId());
        }
    }

    private void prepareBeginAndFinishReview(int examTimeInMiliseconds,
                                             int correctAnswers, int incorrectAnswers) {

        List<FlashcardBlueprint> blueprints = generateFlashcardBlueprints(correctAnswers + incorrectAnswers);

        review = new FlashcardReview(blueprints);
        review.begin();

        waitSomeTime(examTimeInMiliseconds);

        for (int i = 0; i < correctAnswers; i++) {
            review.markFlashcardAsCorrect();
            review.next();
        }

        for (int i = 0; i < incorrectAnswers; i++) {
            review.markFlashcardAsIncorrect();
            review.next();
        }

        review.finish();
        when(flashcardReviewRepository.findById(review.getId()))
                .thenReturn(java.util.Optional.of(review));
    }

    private List<FlashcardBlueprint> generateFlashcardBlueprints(int size) {
        List<FlashcardBlueprint> blueprints = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            blueprints.add(new FlashcardBlueprint("Question-%s".formatted(i),
                    "Answer-%s".formatted(i),
                    new FlashcardMetadata("Source-%s".formatted(i),
                            List.of("Tag-%s.1".formatted(i), "Tag-%s.2".formatted(i)),
                            java.util.Locale.ENGLISH)));
        }

        return blueprints;
    }

    private void waitSomeTime(int timeInMiliseconds) {
        try {
            Thread.sleep(timeInMiliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private long convertDateToEpochMilli(java.time.LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private void assertReviewDataContainsValidDataFromReview(int examTimeInMiliseconds,
                                                             int correctAnswers, int incorrectAnswers) {

        assertEquals(reviewData.getFlashcardCount(), (correctAnswers + incorrectAnswers));
        assertEquals(reviewData.getCorrectAnswers(), correctAnswers);
        assertEquals(reviewData.getIncorrectAnswers(), incorrectAnswers);

        long startDate = convertDateToEpochMilli(reviewData.getStartDate());
        long endDate = convertDateToEpochMilli(reviewData.getFinishDate());

        assertNotEquals(0, startDate);
        assertNotEquals(0, endDate);
        assertNotEquals(startDate, endDate);
        assertNotEquals(endDate - startDate, examTimeInMiliseconds);
    }


}
