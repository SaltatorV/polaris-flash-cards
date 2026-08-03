package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;
import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintCreationEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintDeletionEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintUpdateEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.query.FlashcardBlueprintQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.model.FlashcardBlueprintCreationDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        var blueprint = buildBlueprint()
                .withCategoryId(categoryId)
                .withSource("E2E-Test")
                .addTags("Test", "E2E")
                .defineLocalization()
                .withLocale(Locale.PL)
                .withQuestion("Question-1")
                .withAnswer("Answer-1")
                .createLocalization().createDto();

        // when
        var response = flashcardBlueprintCreationEndpointCaller.executeCreateAPICall(List.of(blueprint)).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // then
        flashcardBlueprintQueryEndpointCaller.getFlashcardBlueprintByCategory(categoryId);
    }

    @Test
    public void testShouldCreateMultipleBlueprints() {

    }

    @Test
    public void testShouldCreateBlueprintWithSingleLocalizations() {

    }

    @Test
    public void testShouldNotAllowToCreateBlueprintWithoutAtLeastOneLocalization() {

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

    private FlashcardBlueprintCreationDtoBuilder buildBlueprint() {
        return new FlashcardBlueprintCreationDtoBuilder();
    }

    private void assertListOfCategoriesContainsOnly(String... categories) {
        List<String> expectedCategories = List.of(categories);
        assertTrue(listOfCategories.stream().map(dto -> dto.getCategoryName())
                .allMatch(expectedCategories::contains));
    }
}
