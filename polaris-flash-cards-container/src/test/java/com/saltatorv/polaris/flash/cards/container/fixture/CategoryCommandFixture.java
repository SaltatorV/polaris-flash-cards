package com.saltatorv.polaris.flash.cards.container.fixture;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;

public class CategoryCommandFixture {
    public static final String DEFAULT_CATEGORY = "Java";
    public static final String ROOT_CATEGORY = "Programming";

    public static CategoryDto rootCategory() {
        return new CategoryDto(ROOT_CATEGORY, null);
    }

    public static CategoryDto childCategory(String name, String parentCategoryId) {
        return new CategoryDto(name, parentCategoryId);
    }
}
