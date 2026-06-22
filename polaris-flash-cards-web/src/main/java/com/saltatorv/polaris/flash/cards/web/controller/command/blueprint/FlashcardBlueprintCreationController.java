package com.saltatorv.polaris.flash.cards.web.controller.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.FlashcardBlueprintCreateCommandFacade;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlueprintCreationController extends BaseController {
    private static final String BASE_ENDPOINT = "/flashcard/blueprint";

    public static final String FLASHCARD_BLUEPRINT_CREATE_ENDPOINT = BASE_ENDPOINT + "/create";

    private final FlashcardBlueprintCreateCommandFacade flashcardBlueprintCreateCommandFacade;

    public FlashcardBlueprintCreationController(FlashcardBlueprintCreateCommandFacade flashcardBlueprintCreateCommandFacade) {
        this.flashcardBlueprintCreateCommandFacade = flashcardBlueprintCreateCommandFacade;
    }

    @PostMapping(FLASHCARD_BLUEPRINT_CREATE_ENDPOINT)
    public void createFlashcardBlueprints(@RequestBody List<FlashcardBlueprintCreateDto> requests) {
        flashcardBlueprintCreateCommandFacade.addFlashcardBlueprints(requests);
    }

}
