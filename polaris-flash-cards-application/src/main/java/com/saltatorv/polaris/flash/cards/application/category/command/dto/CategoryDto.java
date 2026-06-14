package com.saltatorv.polaris.flash.cards.application.category.command.dto;

public class CategoryDto {
    private String categoryName;
    private String parentCategoryId;

    public CategoryDto(String categoryName, String parentCategoryId) {
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
    }

    public CategoryDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }
}
