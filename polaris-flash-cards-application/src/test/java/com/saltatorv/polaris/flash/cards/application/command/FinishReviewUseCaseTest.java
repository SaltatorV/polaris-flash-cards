package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinishReviewUseCaseTest {

    private FlashcardReview review;

    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;
    @InjectMocks
    private FinishReviewUseCase finishReviewUseCase;

    @BeforeEach
    public void setUp() {
        review = null;
    }

    @Test
    public void testShouldFinishReview() {
        //given
        generateReviewForRepository();
        beginReview();

        //when
        finishReview();

        //then
        assertReviewIsFinished();

    }

    @Test
    public void testShouldThrowExceptionWhenReviewNotFound() {
        //given

        //when
        assertThrows(RuntimeException.class, this::finishReview);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenReviewNotStarted() {
        //given
        generateReviewForRepository();

        //when
        assertThrows(RuntimeException.class, this::finishReview);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenReviewAlreadyFinished() {
        //given
        generateReviewForRepository();
        beginReview();
        finishReview();

        //when
        assertThrows(RuntimeException.class, this::finishReview);

        //then
    }

    private void beginReview() {
        review.begin();
    }

    private void finishReview() {
        finishReviewUseCase.finishReview(review.getId());
    }

    private void generateReviewForRepository() {
        List<FlashcardBlueprint> blueprints = generateFlashcardBlueprints(10);
        review = new FlashcardReview(blueprints);
        when(flashcardReviewRepository.findById(any(FlashcardReviewId.class)))
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
    private void assertReviewIsFinished() {
        long startDate = convertDateToEpochMilli(review.getStartDate());
        long endDate = convertDateToEpochMilli(review.getFinishDate());

        assertNotEquals(0, startDate);
        assertNotEquals(0, endDate);
    }

    private long convertDateToEpochMilli(java.time.LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
