package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.container.caller.blueprint.FlashcardBlueprintEndpointCallerImplementation;
import com.saltatorv.polaris.flash.cards.container.caller.flashcard.FlashcardReviewEndpointCallerImplementation;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.e2e.model.FlashcardReviewAnswers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TakeFlashcardReviewE2ETest extends BaseE2ETest {
    private final static int BLUEPRINT_MULTIPLIER = 2;

    static Stream<Arguments> provideCorrectAndIncorrectAnswersForReview() {
        return Stream.of(
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(false, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, true, false, true, false, true, false, true, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, true, false))
        );
    }

    @MethodSource("provideCorrectAndIncorrectAnswersForReview")
    @ParameterizedTest
    void testShouldAddFlashcardBlueprint(FlashcardReviewAnswers flashcardReviewAnswers) {
        //given
        FlashcardBlueprintEndpointCallerImplementation.build()
                .createBlueprint()
                .addDefaultBlueprintToRequestBody(flashcardReviewAnswers.answers().size() * BLUEPRINT_MULTIPLIER)
                .executeCreateAPICall();

        //when
        var caller = FlashcardReviewEndpointCallerImplementation.build()
                .generateRandomFlashcardReview(flashcardReviewAnswers.answers().size())
                .begin();

        for (Boolean answer : flashcardReviewAnswers.answers()) {
            caller.drawNext();
            if (answer) {
                caller.markAsCorrect();
            } else {
                caller.markAsIncorrect();
            }
        }

        caller.finish();
    }
}
