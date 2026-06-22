package com.saltatorv.polaris.flash.cards.web.controller.query.category;

import com.saltatorv.polaris.flash.cards.application.category.query.CategoryQueryFacade;
import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryQueryController {
    public final static String BASE_CATEGORY_QUERY_ENDPOINT = "/flashcard/category";

    private final CategoryQueryFacade categoryQueryFacade;

    public CategoryQueryController(CategoryQueryFacade categoryQueryFacade) {
        this.categoryQueryFacade = categoryQueryFacade;
    }

    @GetMapping(BASE_CATEGORY_QUERY_ENDPOINT)
    public List<CategoryDto> getCategory(@RequestParam(name = "depth", defaultValue = "1") int depth) {
        return categoryQueryFacade.getCategoriesForSpecificDepth(depth);
    }

}
