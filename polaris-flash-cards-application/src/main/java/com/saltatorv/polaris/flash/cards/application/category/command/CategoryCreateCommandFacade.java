package com.saltatorv.polaris.flash.cards.application.category.command;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryCreateCommandFacade {

    private final AddCategoryUseCase addCategoryUseCase;

    public CategoryCreateCommandFacade(AddCategoryUseCase addCategoryUseCase) {
        this.addCategoryUseCase = addCategoryUseCase;
    }

    public void addNewCategory(CategoryDto categoryDto) {
        addCategoryUseCase.addCategory(categoryDto);
    }
}
