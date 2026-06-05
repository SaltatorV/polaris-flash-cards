package com.saltatorv.polaris.flash.cards.application.category.query.dto;

public class CategoryDto {
    private final String categoryName;

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
