package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintCreationEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.command.FlashcardReviewLifecycleCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.command.FlashcardReviewOperationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.query.FlashcardReviewQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.e2e.model.FlashcardReviewAnswers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardReviewE2ETest extends BaseE2ETest {

    //blueprint caller
    FlashcardBlueprintCreationEndpointCaller blueprintCreationEndpointCaller;

    //review caller
    FlashcardReviewLifecycleCommandEndpointCaller reviewLifecycleCommandCaller;
    FlashcardReviewOperationCommandEndpointCaller reviewOperationCommandCaller;
    FlashcardReviewQueryEndpointCaller reviewQueryCaller;

    static Stream<Arguments> provideCorrectAndIncorrectAnswersForReview() {
        return Stream.of(
                Arguments.of(FlashcardReviewAnswers.of(true)),
                Arguments.of(FlashcardReviewAnswers.of(false)),
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(false, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, true, false, true, false, true, false, true, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, true, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, true, false, true, true, false, true, false, false, true, false, true))
        );
    }

    @BeforeEach
    public void setup() {
        blueprintCreationEndpointCaller = FlashcardBlueprintCreationEndpointCaller.build();

        reviewLifecycleCommandCaller = FlashcardReviewLifecycleCommandEndpointCaller.build();
        reviewOperationCommandCaller = FlashcardReviewOperationCommandEndpointCaller.build();
        reviewQueryCaller = FlashcardReviewQueryEndpointCaller.build();
    }

    @MethodSource("provideCorrectAndIncorrectAnswersForReview")
    @ParameterizedTest
    void testShouldAddFlashcardBlueprint(FlashcardReviewAnswers predictedResult) {
        //given
        var drewQuestions = new ArrayList<String>();

        var blueprintsToCreate = createDefaultBlueprints(predictedResult.answers().size() * 2);

        blueprintCreationEndpointCaller.executeCreateAPICall(blueprintsToCreate);

        //when

        var createdReviewId = reviewLifecycleCommandCaller.generateRandomFlashcardReview(predictedResult.answers().size());

        reviewOperationCommandCaller.setupReview(createdReviewId);

        reviewOperationCommandCaller.begin();

        for (Boolean answer : predictedResult.answers()) {
            reviewOperationCommandCaller.drawNext(drewQuestions);
            if (answer) {
                reviewOperationCommandCaller.markAsCorrect();
            } else {
                reviewOperationCommandCaller.markAsIncorrect();
            }
        }

        reviewOperationCommandCaller.finish();

        FlashcardReviewDataDto reviewResult = reviewQueryCaller.getReview(reviewOperationCommandCaller.getCurrentFlashcardReviewId());

        assertDrewQuestionCount(predictedResult, drewQuestions);
        assertEveryDrewQuestionIsDifferent(drewQuestions);
        assertFlashcardCount(predictedResult, reviewResult);
        assertCorrectAnswerCountIs(predictedResult, reviewResult);
        assertIncorrectAnswerCountIs(predictedResult, reviewResult);
    }


    private void assertDrewQuestionCount(FlashcardReviewAnswers flashcardReviewAnswers, ArrayList<String> drewQuestions) {
        assertEquals(flashcardReviewAnswers.answers().size(), drewQuestions.size());
    }

    private void assertEveryDrewQuestionIsDifferent(ArrayList<String> drewQuestions) {
        assertEquals(drewQuestions.stream().distinct().count(), drewQuestions.size());
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

    public List<FlashcardBlueprintCreateDto> createDefaultBlueprints(int times) {

        List<FlashcardBlueprintCreateDto> dtos = new ArrayList<>();

        for (int i = 0; i < times; i++) {

            List<FlashcardLocalizationCreateDto> localizationCreateDtos =
                    List.of(new FlashcardLocalizationCreateDto(Locale.ENGLISH.toLanguageTag(), String.format("Question-%d", i),
                            String.format("Definition-%d", i)));

            dtos.add(new FlashcardBlueprintCreateDto(
                    "01976e3e-6c52-7000-8c3f-2c4e5d6f7a8b",
                    String.format("Source-%d", i),
                    Set.of(String.format("Tags-%d", i)),
                    localizationCreateDtos
            ));
        }

        return dtos;
    }
}
