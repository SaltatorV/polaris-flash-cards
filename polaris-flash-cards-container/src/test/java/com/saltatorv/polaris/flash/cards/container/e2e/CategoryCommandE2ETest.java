package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.web.handler.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandE2ETest extends BaseE2ETest {
    // category caller
    CategoryQueryEndpointCaller categoryQueryEndpointCaller;
    CategoryCreationCommandEndpointCaller categoryCreationCommandEndpointCaller;

    @BeforeEach
    public void setup() {
        categoryQueryEndpointCaller = CategoryQueryEndpointCaller.build();
        categoryCreationCommandEndpointCaller = CategoryCreationCommandEndpointCaller.build();
    }


    @Test
    void testShouldManageCompleteCategoryLifecycle() {
        // given
        var category = new CategoryDto("Programming", null);

        // when
        categoryCreationCommandEndpointCaller.createCategory(category);

        // then
        var listOfCategories = categoryQueryEndpointCaller.getCategory(null);
        assertEquals(2, listOfCategories.size());

        var programmingCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Programming")).findFirst();
        var javaCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Java")).findFirst();
        assertCategoryNameIsEqualTo(programmingCategory, "Programming");
        assertCategoryNameIsEqualTo(javaCategory, "Java");

        // when && then
        var response = categoryCreationCommandEndpointCaller.createCategory(category).getLastResponse();
        assertTrue(response.getStatusCode() == 409);
        var expectedErrorResponse = new ErrorResponse("CATEGORY_DUPLICATED", "Category with name: Programming already exists on depth level: 1");
        assertEquals(createJsonFrom(expectedErrorResponse), response.getBody().asString());


        //when
        var parentCategoryId = programmingCategory.get().getId();
        var secondCategory = new CategoryDto("Domain-Driven-Design", parentCategoryId);
        categoryCreationCommandEndpointCaller.createCategory(secondCategory);
        listOfCategories = categoryQueryEndpointCaller.getCategory(parentCategoryId);

        //then
        assertEquals(1, listOfCategories.size());
        var dddCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Domain-Driven-Design")).findFirst();
        assertCategoryNameIsEqualTo(dddCategory, "Domain-Driven-Design");

        //when
        parentCategoryId = dddCategory.get().getId();
        var thirdCategory = new CategoryDto("Building blocks", parentCategoryId);
        categoryCreationCommandEndpointCaller.createCategory(thirdCategory);
        listOfCategories = categoryQueryEndpointCaller.getCategory(parentCategoryId);

        //then
        assertEquals(1, listOfCategories.size());
        var buildingBlocks = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Building blocks")).findFirst();
        assertCategoryNameIsEqualTo(buildingBlocks, "Building blocks");

        //when
        parentCategoryId = buildingBlocks.get().getId();
        var fourthCategory = new CategoryDto("Aggregates", parentCategoryId);
        categoryCreationCommandEndpointCaller.createCategory(fourthCategory);
        listOfCategories = categoryQueryEndpointCaller.getCategory(parentCategoryId);

        //then
        assertEquals(1, listOfCategories.size());
        var aggregates = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Aggregates")).findFirst();
        assertCategoryNameIsEqualTo(aggregates, "Aggregates");

        //when
        parentCategoryId = aggregates.get().getId();
        var fifthCategory = new CategoryDto("TOO-DEEP", parentCategoryId);


        //then
        response = categoryCreationCommandEndpointCaller.createCategory(fifthCategory).getLastResponse();
        assertTrue(response.getStatusCode() == 409);
        expectedErrorResponse = new ErrorResponse("CATEGORY_IS_TOO_DEEP", "Max depth reached when create category with name: %s from parent: %s"
                .formatted(fifthCategory.getCategoryName(), fifthCategory.getParentCategoryId()));
        assertEquals(createJsonFrom(expectedErrorResponse), response.getBody().asString());

        //when
        var sixthCategory = new CategoryDto("Many-Categories-1", buildingBlocks.get().getId());
        var seventhCategory = new CategoryDto("Many-Categories-2", buildingBlocks.get().getId());
        var eighthCategory = new CategoryDto("Many-Categories-3", buildingBlocks.get().getId());
        var ninthCategory = new CategoryDto("Many-Categories-4", buildingBlocks.get().getId());
        var tenthCategory = new CategoryDto("Many-Categories-5", buildingBlocks.get().getId());

        categoryCreationCommandEndpointCaller.createCategory(sixthCategory);
        categoryCreationCommandEndpointCaller.createCategory(seventhCategory);
        categoryCreationCommandEndpointCaller.createCategory(eighthCategory);
        categoryCreationCommandEndpointCaller.createCategory(ninthCategory);
        categoryCreationCommandEndpointCaller.createCategory(tenthCategory);

        //then
        listOfCategories = categoryQueryEndpointCaller.getCategory(buildingBlocks.get().getId());
        assertEquals(6, listOfCategories.size());
    }

    private void assertCategoryNameIsEqualTo(Optional<com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto> category, String expectedName) {
        assertTrue(category.isPresent());
        assertCategoryNameIsEqualTo(category.get(), expectedName);
    }

    private void assertCategoryNameIsEqualTo(com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto category, String expectedName) {
        assertEquals(expectedName, category.getCategoryName());
    }
}
