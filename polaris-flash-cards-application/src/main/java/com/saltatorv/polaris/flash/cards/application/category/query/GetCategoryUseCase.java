package com.saltatorv.polaris.flash.cards.application.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.domain.CategoryRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.CategorySnapshot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<CategoryDto> getCategoriesViaParentIfExists(String parentCategoryId) {
        List<CategorySnapshot> categories = categoryRepository.findByParentId(new CategoryId(parentCategoryId));
        if (categories.isEmpty()) {
            categories = categoryRepository.findByDepth(1);
        }
        return categories
                .stream()
                .map(this::mapCategoryToDto)
                .toList();
    }

    private CategoryDto mapCategoryToDto(CategorySnapshot snapshot) {
        return new CategoryDto(snapshot.getId(), snapshot.getCategoryName());
    }
}
