package com.saltatorv.polaris.flash.cards.application.category.command.dto;

public class CategoryDto {
    private final String categoryName;
    private final String parentCategoryId;

    public CategoryDto(String categoryName, String parentCategoryId) {
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }
}
