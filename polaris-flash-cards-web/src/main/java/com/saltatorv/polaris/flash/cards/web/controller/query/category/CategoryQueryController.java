package com.saltatorv.polaris.flash.cards.web.controller.query.category;

import com.saltatorv.polaris.flash.cards.application.category.query.CategoryQueryFacade;
import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryQueryController extends BaseController {
    private final static String BASE_CATEGORY_QUERY_ENDPOINT = "/flashcard/category";

    public final static String CATEGORY_GET_ENDPOINT = BASE_CATEGORY_QUERY_ENDPOINT;

    private final CategoryQueryFacade categoryQueryFacade;

    public CategoryQueryController(CategoryQueryFacade categoryQueryFacade) {
        this.categoryQueryFacade = categoryQueryFacade;
    }

    @GetMapping(CATEGORY_GET_ENDPOINT)
    public List<CategoryDto> getCategory(@RequestParam(name = "parentCategoryId") String parentCategoryId) {
        return categoryQueryFacade.getCategoriesForSpecificDepth(parentCategoryId);
    }

}
