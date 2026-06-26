package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void testShouldCreateCategory() {
        // given
        var category = new CategoryDto("Programming", null);

        // when
        categoryCreationCommandEndpointCaller.createCategory(category);

        // then
        var listOfCategories = categoryQueryEndpointCaller.getCategory(1);

        var programmingCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Programming")).findFirst();
        var javaCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Java")).findFirst();

        assertNotNull(programmingCategory);
        assertNotNull(javaCategory);
    }
}
