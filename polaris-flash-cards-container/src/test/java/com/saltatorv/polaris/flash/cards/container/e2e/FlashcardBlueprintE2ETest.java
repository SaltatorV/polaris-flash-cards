package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintCreationEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintDeletionEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintUpdateEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.query.FlashcardBlueprintQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.model.ErrorResponseDto;
import com.saltatorv.polaris.flash.cards.container.model.ValidationErrorDto;
import com.saltatorv.polaris.flash.cards.container.model.ValidationErrorResponseDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.container.fixture.FlashcardBlueprintCommandFixture.defaultBlueprint;
import static com.saltatorv.polaris.flash.cards.container.validator.ErrorValidator.assertErrorContainError;
import static com.saltatorv.polaris.flash.cards.container.validator.ValidationErrorValidator.assertErrorContainValidationError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlashcardBlueprintE2ETest extends BaseE2ETest {

    // category callers
    CategoryQueryEndpointCaller categoryQueryEndpointCaller;
    CategoryCreationCommandEndpointCaller categoryCreationCommandEndpointCaller;

    // blueprint callers
    FlashcardBlueprintCreationEndpointCaller flashcardBlueprintCreationEndpointCaller;
    FlashcardBlueprintDeletionEndpointCaller flashcardBlueprintDeletionEndpointCaller;
    FlashcardBlueprintUpdateEndpointCaller flashcardBlueprintUpdateEndpointCaller;

    FlashcardBlueprintQueryEndpointCaller flashcardBlueprintQueryEndpointCaller;

    //model
    List<CategoryDto> listOfCategories;

    @BeforeEach
    public void setup() {
        flashcardBlueprintCreationEndpointCaller = FlashcardBlueprintCreationEndpointCaller.build();
        flashcardBlueprintDeletionEndpointCaller = FlashcardBlueprintDeletionEndpointCaller.build();
        flashcardBlueprintUpdateEndpointCaller = FlashcardBlueprintUpdateEndpointCaller.build();
        flashcardBlueprintQueryEndpointCaller = FlashcardBlueprintQueryEndpointCaller.build();

        categoryQueryEndpointCaller = CategoryQueryEndpointCaller.build();
        categoryCreationCommandEndpointCaller = CategoryCreationCommandEndpointCaller.build();
        listOfCategories = new ArrayList<>();
    }

    @Test
    public void testShouldCreateBlueprintWithMultipleLocalizations() {
        // given
        // Get the root category defined in the database migration
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Java");

        // Create child for root category and ensure it is created
        var categoryId = listOfCategories.getFirst().getId();
        var blueprint = defaultBlueprint(categoryId).createDto();

        // when
        var response = flashcardBlueprintCreationEndpointCaller.executeCreateAPICall(List.of(blueprint)).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // then
        flashcardBlueprintQueryEndpointCaller.getFlashcardBlueprintByCategory(categoryId);
        response = flashcardBlueprintQueryEndpointCaller.getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseContainListOfBlueprintSummaries(response, blueprint);
    }

    @Test
    public void testShouldCreateMultipleBlueprints() {
        // given
        // Get the root category defined in the database migration
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Java");

        // Create child for root category and ensure it is created
        var categoryId = listOfCategories.getFirst().getId();
        var firstBlueprint = defaultBlueprint(categoryId).createDto();

        var secondBlueprint = defaultBlueprint(categoryId)
                .nullifyLocalizations()
                .defineLocalization()
                .withLocale(Locale.PL)
                .withQuestion("pytanie-1")
                .withAnswer("odpowiedz-1")
                .createLocalization()
                .createDto();

        // when
        var response = flashcardBlueprintCreationEndpointCaller.executeCreateAPICall(List.of(firstBlueprint, secondBlueprint))
                .getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // then
        flashcardBlueprintQueryEndpointCaller.getFlashcardBlueprintByCategory(categoryId);
        response = flashcardBlueprintQueryEndpointCaller.getLastResponse();
        assertResponseCodeIs200(response);
        assertResponseContainListOfBlueprintSummaries(response, firstBlueprint, secondBlueprint);
    }

    @Test
    public void testShouldNotAllowToCreateBlueprintWithoutAtLeastOneLocalization() {
        // given
        // Get the root category defined in the database migration
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Java");

        // Create child for root category and ensure it is created
        var categoryId = listOfCategories.getFirst().getId();
        var firstBlueprint = defaultBlueprint(categoryId)
                .nullifyLocalizations()
                .createDto();

        // when
        var response = flashcardBlueprintCreationEndpointCaller.executeCreateAPICall(List.of(firstBlueprint)).getLastResponse();

        // then
        assertResponseCodeIs422(response);
        var error = response.as(ValidationErrorResponseDto.class);

        assertErrorContainError(error)
                .elementsToValidate(
                        ErrorResponseDto::getCode,
                        ErrorResponseDto::getType,
                        ErrorResponseDto::getStatus)
                .expectedData("validation-error",
                        "urn:polaris-flash-cards:problem:validation-error",
                        422)
                .validate();

        assertErrorContainValidationError(error)
                .elementsToValidate(ValidationErrorDto::field, ValidationErrorDto::errorCode)
                .expectedErrors(
                        tuple("localizations", "NotEmpty")
                )
                .validate();
    }

    @Test
    public void testShouldAllowToUpdateBlueprint() {

    }

    @Test
    public void testShouldCreateBlueprintUnderSubCategory() {

    }

    @Test
    public void testShouldNotAllowToCreateBlueprintUnderNonExistingCategory() {

    }

    private void assertListOfCategoriesContainsOnly(String... categories) {
        List<String> expectedCategories = List.of(categories);
        assertTrue(listOfCategories.stream().map(dto -> dto.getCategoryName())
                .allMatch(expectedCategories::contains));
    }

    private void assertResponseContainListOfBlueprintSummaries(Response response, FlashcardBlueprintCreateDto... blueprints) {
        List<FlashcardBlueprintSummaryQueryDto> listOfSummaries = response.jsonPath()
                .getList(".", FlashcardBlueprintSummaryQueryDto.class);

        assertThat(listOfSummaries)
                .allSatisfy(dto -> assertThat(dto.getFlashcardBlueprintId()).isNotNull());

        assertThat(listOfSummaries)
                .extracting(
                        dto -> tuple(
                                dto.getAvailableLocalizations().stream().sorted().toList(),
                                dto.getSource(),
                                dto.getTags().stream().sorted().toList()
                        )
                )
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.stream(blueprints).map(
                                dto -> tuple(
                                        dto.getLocalizations().stream().map(localization -> localization.getLocale().code()).sorted().toList(),
                                        dto.getSource(),
                                        dto.getTags().stream().sorted().toList()
                                )).toList()
                );
    }

}
