package com.saltatorv.polaris.flash.cards.application.category.query.dto;

public class CategoryDto {
    private String id;
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getId() {
        return id;
    }
}
