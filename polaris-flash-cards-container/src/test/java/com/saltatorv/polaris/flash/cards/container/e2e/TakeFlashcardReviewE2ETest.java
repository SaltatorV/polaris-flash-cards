package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.query.review.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.FlashcardBlueprintEndpointCallerImplementation;
import com.saltatorv.polaris.flash.cards.container.caller.flashcard.FlashcardReviewEndpointCallerImplementation;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.e2e.model.FlashcardReviewAnswers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TakeFlashcardReviewE2ETest extends BaseE2ETest {

    static Stream<Arguments> provideCorrectAndIncorrectAnswersForReview() {
        return Stream.of(
                Arguments.of(FlashcardReviewAnswers.of(true)),
                Arguments.of(FlashcardReviewAnswers.of(false)),
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
        var drewQuestions = new ArrayList<String>();
        FlashcardBlueprintEndpointCallerImplementation.build()
                .createBlueprint()
                .addDefaultBlueprintToRequestBody(flashcardReviewAnswers.answers().size() * 2)
                .executeCreateAPICall();

        //when
        var caller = FlashcardReviewEndpointCallerImplementation.build()
                .generateRandomFlashcardReview(flashcardReviewAnswers.answers().size())
                .begin();

        for (Boolean answer : flashcardReviewAnswers.answers()) {
            caller.drawNext(drewQuestions);
            if (answer) {
                caller.markAsCorrect();
            } else {
                caller.markAsIncorrect();
            }
        }

        caller.finish();

        FlashcardReviewDataDto reviewResult = caller.view().getReview();

        assertDrewQuestionCount(flashcardReviewAnswers, drewQuestions);
        assertEveryDrewQuestionIsDifferent(drewQuestions);
        assertFlashcardCount(flashcardReviewAnswers, reviewResult);
        assertCorrectAnswerCountIs(flashcardReviewAnswers, reviewResult);
        assertIncorrectAnswerCountIs(flashcardReviewAnswers, reviewResult);
    }


    private void assertDrewQuestionCount(FlashcardReviewAnswers flashcardReviewAnswers, ArrayList<String> drewQuestions) {
        assertEquals(flashcardReviewAnswers.answers().size(), drewQuestions.size());
    }

    private void assertEveryDrewQuestionIsDifferent(ArrayList<String> drewQuestions) {
        assertTrue(drewQuestions.stream().distinct().count() == drewQuestions.size());
    }

    private void assertFlashcardCount(FlashcardReviewAnswers flashcardReviewAnswers, FlashcardReviewDataDto reviewResult) {
        assertEquals(0, reviewResult.getNotAnsweredCount());
        assertEquals(flashcardReviewAnswers.answers().size(), reviewResult.getFlashcardCount());
    }

    private void assertCorrectAnswerCountIs(FlashcardReviewAnswers flashcardReviewAnswers, FlashcardReviewDataDto reviewResult) {
        long expectedCorrectAnswerCount =
                flashcardReviewAnswers.answers().stream()
                        .filter(Boolean.TRUE::equals)
                        .count();

        assertEquals(expectedCorrectAnswerCount, reviewResult.getCorrectAnswers());
    }

    private void assertIncorrectAnswerCountIs(FlashcardReviewAnswers flashcardReviewAnswers, FlashcardReviewDataDto reviewResult) {
        long expectedIncorrectAnswerCount =
                flashcardReviewAnswers.answers().stream()
                        .filter(Boolean.FALSE::equals)
                        .count();

        assertEquals(expectedIncorrectAnswerCount, reviewResult.getIncorrectAnswers());
    }
}
