package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.command.review.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.NoMoreQuestionsInFlashcardReviewDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NextFlashcardFromReviewUseCaseTest {
    private final static int FLASHCARDS_BLUEPRINTS_SIZE = 10;

    private FlashcardReview review;
    private FlashcardDto flashcardDto;
    private List<FlashcardBlueprint> blueprints;

    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;

    @InjectMocks
    private NextFlashcardFromReviewUseCase nextFlashcardFromReviewUseCase;

    @BeforeEach
    public void setUp() {
        review = null;
        flashcardDto = null;
        blueprints = new ArrayList<>();
    }

    @Test
    public void testShouldThrowExceptionWhenReviewNotFound() {
        //given

        //when
        assertThrows(RuntimeException.class, this::next);

        //then

    }

    @Test
    public void testShouldPassNextQuestion() {
        //given
        generateReviewForRepository();
        beginReview();

        //when & then
        for (int i = 0; i < FLASHCARDS_BLUEPRINTS_SIZE; i++) {
            next();
            assertFlashcardDtoIsEqualToFlashcard(i);
        }
    }

    private void assertFlashcardDtoIsEqualToFlashcard(int index) {
        Flashcard flashcard = blueprints.get(index).createFlashcard();
        assertEquals(flashcard.getQuestion(), flashcardDto.getQuestion());
        assertEquals(flashcard.getDefinition(), flashcardDto.getDefinition());
    }


    @Test
    public void testShouldThrowExceptionWhenReviewNotStarted() {
        //given
        generateReviewForRepository();

        //when
        assertThrows(FlashcardReviewNotStartedDomainException.class, this::next);
    }

    @Test
    public void testShouldThrowExceptionWhenReviewFinished() {
        //given
        generateReviewForRepository();
        beginReview();

        //when
        for (int i = 0; i < FLASHCARDS_BLUEPRINTS_SIZE; i++) {
            next();
        }

        assertThrows(NoMoreQuestionsInFlashcardReviewDomainException.class, this::next);
    }

    @Test
    public void testShouldThrowExceptionWhenThereIsNoFlashcardsLeft() {
        //given
        generateReviewForRepository();
        beginReview();
        finishReview();

        //when
        assertThrows(FlashcardReviewAlreadyFinishedDomainException.class, this::next);
    }

    private void next() {
        flashcardDto = nextFlashcardFromReviewUseCase.nextFlashcardDto(review.getId());
    }

    private void beginReview() {
        review.begin();
    }

    private void finishReview() {
        review.finish();
    }

    private void generateReviewForRepository() {
        blueprints = generateFlashcardBlueprints(FLASHCARDS_BLUEPRINTS_SIZE);
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
}
