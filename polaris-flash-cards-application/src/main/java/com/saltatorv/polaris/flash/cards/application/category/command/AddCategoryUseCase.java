package com.saltatorv.polaris.flash.cards.application.category.command;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.domain.Category;
import com.saltatorv.polaris.flash.cards.domain.CategoryRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

import java.util.Optional;

class AddCategoryUseCase {

    private final CategoryRepository categoryRepository;

    AddCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    void addCategory(CategoryDto categoryDto) {

        Category parentCategory = null;
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(new CategoryId(categoryDto.getParentCategoryId()));
            if (optionalCategory.isEmpty()) {
                throw new IllegalArgumentException("Parent category with id " + categoryDto.getParentCategoryId() + " does not exist.");
            }
            parentCategory = optionalCategory.get();
        }

        Category categoryToSave;
        if (parentCategory == null) {
            categoryToSave = new Category(categoryDto.getCategoryName());
        } else {
            categoryToSave = parentCategory.createChild(categoryDto.getCategoryName());
        }

        Optional<Category> duplicatedCategory = categoryRepository.findByNameAndDepth(categoryToSave.getCategoryName(), categoryToSave.getDepth());

        if (duplicatedCategory.isPresent()) {
            throw new IllegalArgumentException("Category with name: %s already exists on depth level: %s"
                    .formatted(categoryToSave.getCategoryName(), categoryToSave.getDepth()));
        }

        categoryRepository.save(categoryToSave);

    }
}
