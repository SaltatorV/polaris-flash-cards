package com.saltatorv.polaris.flash.cards.web.controller.command.category;

import com.saltatorv.polaris.flash.cards.application.category.command.CategoryCreateCommandFacade;
import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryCreationController {
    private final static String BASE_LIFECYCLE_ENDPOINT = "/flashcard/category";

    public final static String CATEGORY_CREATE_ENDPOINT = BASE_LIFECYCLE_ENDPOINT + "/create";

    private final CategoryCreateCommandFacade categoryCreateCommandFacade;

    public CategoryCreationController(CategoryCreateCommandFacade categoryCreateCommandFacade) {
        this.categoryCreateCommandFacade = categoryCreateCommandFacade;
    }

    @PostMapping(CATEGORY_CREATE_ENDPOINT)
    public void createCategory(@RequestBody CategoryDto dto) {
        categoryCreateCommandFacade.addNewCategory(dto);
    }
}
