package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;
import com.saltatorv.polaris.flash.cards.application.review.command.dto.FlashcardDto;
import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintCreationEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.command.FlashcardReviewLifecycleCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.command.FlashcardReviewOperationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.review.query.FlashcardReviewQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.e2e.model.FlashcardReviewAnswers;
import com.saltatorv.polaris.flash.cards.web.handler.ErrorResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlashcardReviewE2ETest extends BaseE2ETest {

    private final static String UUID_V7_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-7[0-9a-fA-F]{3}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

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
                Arguments.of(FlashcardReviewAnswers.of((Boolean) null)),
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(false, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(null, null, null, null, null, null, null, null, null, null)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, false, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, true, true, true, true, true, true, true, true, true)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, true, false, true, false, true, false, true, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, false, false, false, false, false, true, false)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, false, true, false, true, true, false, true, false, false, true, false, true)),
                Arguments.of(FlashcardReviewAnswers.of(true, false, null, null, false, true, null, false, true, null, false, true, null, true))
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
    void testShouldTakeFlashcardReview(FlashcardReviewAnswers predictedResult) {
        //given
        var startTime = System.currentTimeMillis();
        var drewQuestions = new ArrayList<String>();

        var blueprintsToCreate = createDefaultBlueprints(predictedResult.answers().size() * 2);

        var response = blueprintCreationEndpointCaller.executeCreateAPICall(blueprintsToCreate).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        //when
        var createdReviewId = reviewLifecycleCommandCaller.generateRandomFlashcardReview(predictedResult.answers().size());
        response = reviewLifecycleCommandCaller.getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyMatchRegex(response, UUID_V7_REGEX);

        reviewOperationCommandCaller.setupReview(createdReviewId);

        response = reviewOperationCommandCaller.begin().getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseBodyIsEmpty(response);

        for (Boolean answer : predictedResult.answers()) {
            var next = reviewOperationCommandCaller.drawNext();
            response = reviewOperationCommandCaller.getLastResponse();
            assertResponseCodeIs200(response);
            assertResponseContainQuestionAndAnswer(response);
            drewQuestions.add(next);

            if (answer == null) {
                // not-answered
            } else if (answer) {
                response = reviewOperationCommandCaller.markAsCorrect().getLastResponse();
                assertResponseCodeIs200(response);
                assertResponseBodyIsEmpty(response);
            } else {
                response = reviewOperationCommandCaller.markAsIncorrect().getLastResponse();
                assertResponseCodeIs200(response);
                assertResponseBodyIsEmpty(response);
            }
        }

        response = reviewOperationCommandCaller.finish().getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseBodyIsEmpty(response);

        FlashcardReviewDataDto reviewResult = reviewQueryCaller.getReview(reviewOperationCommandCaller.getCurrentFlashcardReviewId());

        assertResponseCodeIs200(response);
        assertDrewQuestionCount(predictedResult, drewQuestions);
        assertEveryDrewQuestionIsDifferent(drewQuestions);
        assertFlashcardCount(predictedResult, reviewResult);
        assertCorrectAnswerCountIs(predictedResult, reviewResult);
        assertIncorrectAnswerCountIs(predictedResult, reviewResult);
        assertReviewIsInitialized(reviewResult, startTime);
    }


    @Test
    void testShouldTakeFlashcardReview() {
        //given
        var blueprintsToCreate = createDefaultBlueprints(10 * 2);
        var response = blueprintCreationEndpointCaller.executeCreateAPICall(blueprintsToCreate).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        var createdReviewId = reviewLifecycleCommandCaller.generateRandomFlashcardReview(10);
        response = reviewLifecycleCommandCaller.getLastResponse();
        reviewOperationCommandCaller.setupReview(createdReviewId);
        assertResponseCodeIs201(response);
        assertResponseBodyMatchRegex(response, UUID_V7_REGEX);

        //when
        response = reviewOperationCommandCaller.finish().getLastResponse();
        assertResponseCodeIs409(response);
        var expectedErrorResponse = new ErrorResponse("REVIEW_NOT_STARTED", "Review not started");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        reviewOperationCommandCaller.drawNext();
        response = reviewOperationCommandCaller.getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("REVIEW_NOT_STARTED", "Review not started");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        response = reviewOperationCommandCaller.markAsCorrect().getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("NO_FLASHCARD_RECEIVED", "No flashcards were returned from the review process.");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        response = reviewOperationCommandCaller.markAsIncorrect().getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("NO_FLASHCARD_RECEIVED", "No flashcards were returned from the review process.");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        response = reviewOperationCommandCaller.begin().getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseBodyIsEmpty(response);

        response = reviewOperationCommandCaller.begin().getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("REVIEW_ALREADY_STARTED", "Review already started");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        response = reviewOperationCommandCaller.markAsCorrect().getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("NO_FLASHCARD_RECEIVED", "No flashcards were returned from the review process.");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        response = reviewOperationCommandCaller.markAsIncorrect().getLastResponse();
        assertResponseCodeIs409(response);
        expectedErrorResponse = new ErrorResponse("NO_FLASHCARD_RECEIVED", "No flashcards were returned from the review process.");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);

        reviewOperationCommandCaller.drawNext();
        response = reviewOperationCommandCaller.getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseContainQuestionAndAnswer(response);

        response = reviewOperationCommandCaller.markAsIncorrect().getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseBodyIsEmpty(response);

        response = reviewOperationCommandCaller.markAsIncorrect().getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseBodyIsEmpty(response);
    }

    private void assertResponseContainQuestionAndAnswer(Response response) {
        var dto = response.as(FlashcardDto.class);

        assertTrue(dto.getQuestion().matches("^Question-\\d+$"));
        assertTrue(dto.getDefinition().matches("^Definition-\\d+$"));
    }

    public void assertResponseBodyMatchRegex(Response response, String regex) {
        assertTrue(response.asString().matches(regex));
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
                        .filter(answer -> Boolean.FALSE.equals(answer) || answer == null)
                        .count();

        assertEquals(expectedIncorrectAnswerCount, reviewResult.getIncorrectAnswers());
    }

    private void assertReviewIsInitialized(FlashcardReviewDataDto reviewResult, long startTime) {
        assertTrue(startTime < reviewResult.getStartDate());
        assertTrue(reviewResult.getStartDate() < reviewResult.getFinishDate());
        assertTrue(reviewResult.getId().matches(UUID_V7_REGEX));
    }

    public List<FlashcardBlueprintCreateDto> createDefaultBlueprints(int times) {

        List<FlashcardBlueprintCreateDto> dtos = new ArrayList<>();

        for (int i = 0; i < times; i++) {

            List<FlashcardLocalizationCreateDto> localizationCreateDtos =
                    List.of(new FlashcardLocalizationCreateDto(Locale.fromCode("en"), String.format("Question-%d", i),
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
