package com.saltatorv.polaris.flash.cards.application.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.domain.Category;
import com.saltatorv.polaris.flash.cards.domain.CategoryRepository;
import com.saltatorv.polaris.flash.cards.domain.snapshot.CategorySnapshot;

import java.util.List;

class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<CategoryDto> getCategoriesForSpecificDepth(int depth) {
        List<CategorySnapshot> categories = categoryRepository.findByDepth(depth);

        return categories
                .stream()
                .map(category ->
                        new CategoryDto(category.getCategoryName()))
                .toList();
    }
}
