package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyStartedDomainException;
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
public class BeginReviewUseCaseTest {

    private FlashcardReview review;

    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;
    @InjectMocks
    private BeginReviewUseCase beginReviewUseCase;

    @BeforeEach
    public void setUp() {
        review = null;
    }


    @Test
    public void testShouldBeginReview() {
        //given
        generateReviewForRepository();

        //when
        beginReview();

        //then
        assertReviewIsStarted();
    }

    @Test
    public void testShouldThrowExceptionWhenReviewNotFound() {
        //given

        //when
        assertThrows(RuntimeException.class, this::beginReview);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenReviewAlreadyStarted() {
        //given
        generateReviewForRepository();
        beginReview();

        //when
        assertThrows(FlashcardReviewAlreadyStartedDomainException.class, this::beginReview);

        //then

    }

    @Test
    public void testShouldThrowExceptionWhenReviewAlreadyFinished() {
        //given
        generateReviewForRepository();
        beginReview();
        finishReview();

        //when
        assertThrows(FlashcardReviewAlreadyFinishedDomainException.class, this::beginReview);

        //then

    }

    private void beginReview() {
        beginReviewUseCase.beginReview(review.getId());
    }

    private void finishReview() {
        review.finish();
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

    private void assertReviewIsStarted() {
        long startDate = convertDateToEpochMilli(review.getStartDate());
        long endDate = convertDateToEpochMilli(review.getFinishDate());

        assertNotEquals(0, startDate);
        assertEquals(0, endDate);
    }

    private long convertDateToEpochMilli(java.time.LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
