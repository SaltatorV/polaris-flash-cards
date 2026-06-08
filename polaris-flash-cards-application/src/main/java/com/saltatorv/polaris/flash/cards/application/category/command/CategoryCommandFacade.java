package com.saltatorv.polaris.flash.cards.application.category.command;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryCommandFacade {

    private final AddCategoryUseCase addCategoryUseCase;

    public CategoryCommandFacade(AddCategoryUseCase addCategoryUseCase) {
        this.addCategoryUseCase = addCategoryUseCase;
    }

    public void addNewCategory(CategoryDto categoryDto) {
        addCategoryUseCase.addCategory(categoryDto);
    }
}
