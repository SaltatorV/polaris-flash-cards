package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.web.handler.ErrorResponseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.container.fixture.CategoryCommandFixture.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryCommandE2ETest extends BaseE2ETest {
    // category caller
    CategoryQueryEndpointCaller categoryQueryEndpointCaller;
    CategoryCreationCommandEndpointCaller categoryCreationCommandEndpointCaller;

    List<com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto> listOfCategories;

    @BeforeEach
    public void setup() {
        categoryQueryEndpointCaller = CategoryQueryEndpointCaller.build();
        categoryCreationCommandEndpointCaller = CategoryCreationCommandEndpointCaller.build();
        listOfCategories = new ArrayList<>();
    }

    @Test
    void shouldCreateRootCategory() {
        // given
        var category = rootCategory();

        // when
        var response = categoryCreationCommandEndpointCaller.createCategory(category).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // then
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly(DEFAULT_CATEGORY, ROOT_CATEGORY);
    }

    @Test
    void shouldCreateNestedCategoriesAndReturnThemByParentId() {
        // Get the root category defined in the database migration
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly(DEFAULT_CATEGORY);

        // Create child for root category and ensure it is created
        var firstId = listOfCategories.getFirst().getId();
        var second = childCategory("Second", firstId);
        var response = categoryCreationCommandEndpointCaller.createCategory(second).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        listOfCategories = categoryQueryEndpointCaller.getCategory(firstId);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Second");

        // Create a child category under "Second" and verify it was created
        var secondId = listOfCategories.getFirst().getId();
        var third = childCategory("Third", secondId);
        response = categoryCreationCommandEndpointCaller.createCategory(third).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // Verify that all categories are available under the correct parent
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Java");

        listOfCategories = categoryQueryEndpointCaller.getCategory(firstId);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Second");

        listOfCategories = categoryQueryEndpointCaller.getCategory(secondId);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Third");
        var thirdId = listOfCategories.getFirst().getId();

        listOfCategories = categoryQueryEndpointCaller.getCategory(thirdId);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesIsEmpty();
    }

    @Test
    void testShouldReturnAnErrorWhenCreationDuplicateCategory() {
        // given
        var category = rootCategory();
        var response = categoryCreationCommandEndpointCaller.createCategory(category).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        // when
        response = categoryCreationCommandEndpointCaller.createCategory(category).getLastResponse();

        // then
        var expectedErrorResponse = ErrorResponseFactory.businessRuleViolation("CATEGORY_DUPLICATED", 409, "Category with name: Programming already exists on depth level: 1");
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);
    }

    @Test
    void testShouldReturnAnErrorWhenCreationCategoryTooDeeply() {
        // Prepare a category hierarchy to reach last depth level
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly(DEFAULT_CATEGORY);

        var second = childCategory("Second", listOfCategories.getFirst().getId());
        var response = categoryCreationCommandEndpointCaller.createCategory(second).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        listOfCategories = categoryQueryEndpointCaller.getCategory(listOfCategories.getFirst().getId());
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Second");

        var third = childCategory("Third", listOfCategories.getFirst().getId());
        response = categoryCreationCommandEndpointCaller.createCategory(third).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        listOfCategories = categoryQueryEndpointCaller.getCategory(listOfCategories.getFirst().getId());
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Third");

        var fourth = childCategory("Fourth", listOfCategories.getFirst().getId());
        response = categoryCreationCommandEndpointCaller.createCategory(fourth).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        listOfCategories = categoryQueryEndpointCaller.getCategory(listOfCategories.getFirst().getId());
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Fourth");

        // when
        var fifth = childCategory("Fifth", listOfCategories.getFirst().getId());
        response = categoryCreationCommandEndpointCaller.createCategory(fifth).getLastResponse();

        // then
        var expectedErrorResponse = ErrorResponseFactory.businessRuleViolation("CATEGORY_IS_TOO_DEEP", 409, "Max depth reached when create category with name: %s from parent: %s"
                .formatted(fifth.getCategoryName(), fifth.getParentCategoryId()));
        assertExpectedErrorIsEqualToResponse(expectedErrorResponse, 409, response);
    }

    @Test
    public void testShouldCreateASetOfSubCategoriesUnderSpecificDepth() {

        //given
        listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly(DEFAULT_CATEGORY);
        var firstId = listOfCategories.getFirst().getId();

        var first = childCategory("Many-Categories-1", firstId);
        var second = childCategory("Many-Categories-2", firstId);
        var third = childCategory("Many-Categories-3", firstId);
        var fourth = childCategory("Many-Categories-4", firstId);
        var fifth = childCategory("Many-Categories-5", firstId);

        //when
        var response = categoryCreationCommandEndpointCaller.createCategory(first).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        response = categoryCreationCommandEndpointCaller.createCategory(second).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        response = categoryCreationCommandEndpointCaller.createCategory(third).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        response = categoryCreationCommandEndpointCaller.createCategory(fourth).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);
        response = categoryCreationCommandEndpointCaller.createCategory(fifth).getLastResponse();
        assertResponseCodeIs201(response);
        assertResponseBodyIsEmpty(response);

        //then
        listOfCategories = categoryQueryEndpointCaller.getCategory(firstId);
        assertResponseCodeIs200(categoryQueryEndpointCaller.getLastResponse());
        assertListOfCategoriesContainsOnly("Many-Categories-1", "Many-Categories-2",
                "Many-Categories-3", "Many-Categories-4", "Many-Categories-5");
    }

    private void assertListOfCategoriesContainsOnly(String... categories) {
        List<String> expectedCategories = List.of(categories);
        assertTrue(listOfCategories.stream().map(dto -> dto.getCategoryName())
                .allMatch(expectedCategories::contains));
    }

    private void assertListOfCategoriesIsEmpty() {
        assertTrue(listOfCategories.isEmpty());
    }
}
