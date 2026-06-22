package com.saltatorv.polaris.flash.cards.web.controller.query.category;

import com.saltatorv.polaris.flash.cards.application.category.query.CategoryQueryFacade;
import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryViewController {
    private final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/blueprint";

    public final static String CATEGORY_GET_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/category";

    private final CategoryQueryFacade categoryQueryFacade;

    public CategoryViewController(CategoryQueryFacade categoryQueryFacade) {
        this.categoryQueryFacade = categoryQueryFacade;
    }

    @GetMapping(CATEGORY_GET_ENDPOINT)
    public List<CategoryDto> getCategory(@RequestParam(defaultValue = "0") int depth) {
        return categoryQueryFacade.getCategoriesForSpecificDepth(depth);
    }

}
