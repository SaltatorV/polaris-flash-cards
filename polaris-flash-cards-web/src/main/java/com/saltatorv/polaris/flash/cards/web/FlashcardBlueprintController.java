package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.blueprint.lifecycle.AddFlashcardBlueprintUseCase;
import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlueprintController extends BaseController {
    public static final String FLASHCARD_BLUEPRINT_CREATE_ENDPOINT = "/flashcard/blueprint/create";

    private final AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase;

    public FlashcardBlueprintController(AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase) {
        this.addFlashcardBlueprintUseCase = addFlashcardBlueprintUseCase;
    }

    @PostMapping(FLASHCARD_BLUEPRINT_CREATE_ENDPOINT)
    public void createFlashcardBlueprints(@RequestBody List<FlashcardBlueprintDataDto> requests) {
        addFlashcardBlueprintUseCase.addFlashcardBlueprints(requests);
    }
}
