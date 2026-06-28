package com.saltatorv.polaris.flash.cards.application.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQueryFacade {
    private final GetCategoryUseCase getCategoryUseCase;

    public CategoryQueryFacade(GetCategoryUseCase getCategoryUseCase) {
        this.getCategoryUseCase = getCategoryUseCase;
    }

    public List<CategoryDto> getCategoriesForSpecificDepth(String parentCategoryId) {
        return getCategoryUseCase.getCategoriesViaParentIfExists(parentCategoryId);
    }
}
