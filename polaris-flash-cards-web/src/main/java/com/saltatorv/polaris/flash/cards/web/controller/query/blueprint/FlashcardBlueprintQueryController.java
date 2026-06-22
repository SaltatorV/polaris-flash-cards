package com.saltatorv.polaris.flash.cards.web.controller.query.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.FlashcardBlueprintQueryFacade;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlueprintQueryController extends BaseController {
    private final static String BASE_BLUEPRINT_QUERY_ENDPOINT = "/flashcard/blueprint";

    public final static String FLASHCARD_BLUEPRINT_GET_ENDPOINT = BASE_BLUEPRINT_QUERY_ENDPOINT + "/{id}";
    public final static String FLASHCARD_BLUEPRINT_GET_BY_CATEGORY_ENDPOINT = BASE_BLUEPRINT_QUERY_ENDPOINT + "/category/{categoryId}";

    private final FlashcardBlueprintQueryFacade flashcardBlueprintQueryFacade;

    public FlashcardBlueprintQueryController(FlashcardBlueprintQueryFacade flashcardBlueprintQueryFacade) {
        this.flashcardBlueprintQueryFacade = flashcardBlueprintQueryFacade;
    }


    @GetMapping(FLASHCARD_BLUEPRINT_GET_ENDPOINT)
    public FlashcardBlueprintQueryDto getFlashcardBlueprint(@PathVariable String id) {
        return flashcardBlueprintQueryFacade.getFlashcardBlueprint(id);
    }


    @GetMapping(FLASHCARD_BLUEPRINT_GET_BY_CATEGORY_ENDPOINT)
    public List<FlashcardBlueprintSummaryQueryDto> getFlashcardBlueprintByCategory(@PathVariable String categoryId) {
        return flashcardBlueprintQueryFacade.getFlashcardBlueprintSummariesForCategory(categoryId);
    }
}
