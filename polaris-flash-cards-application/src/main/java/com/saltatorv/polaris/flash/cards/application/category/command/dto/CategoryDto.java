package com.saltatorv.polaris.flash.cards.application.category.command.dto;

import com.saltatorv.polaris.flash.cards.application.shared.validation.OptionalUUID;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

    @NotEmpty(message = "Category Name cannot be empty")
    @Size(min = 3, max = 100, message = "Category Name must be between {min} and {max} characters")
    private String categoryName;
    @OptionalUUID(message = "Parent category ID must be empty or valid UUID")
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
