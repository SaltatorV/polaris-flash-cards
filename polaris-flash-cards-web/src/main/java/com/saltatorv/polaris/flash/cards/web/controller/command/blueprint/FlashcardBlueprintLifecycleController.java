package com.saltatorv.polaris.flash.cards.web.controller.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.command.blueprint.lifecycle.AddFlashcardBlueprintUseCase;
import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import com.saltatorv.polaris.flash.cards.web.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlueprintLifecycleController extends BaseController {
    public static final String FLASHCARD_BLUEPRINT_CREATE_ENDPOINT = "/flashcard/blueprint/create";

    private final AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase;

    public FlashcardBlueprintLifecycleController(AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase) {
        this.addFlashcardBlueprintUseCase = addFlashcardBlueprintUseCase;
    }

    @PostMapping(FLASHCARD_BLUEPRINT_CREATE_ENDPOINT)
    public void createFlashcardBlueprints(@RequestBody List<FlashcardBlueprintDataDto> requests) {
        addFlashcardBlueprintUseCase.addFlashcardBlueprints(requests);
    }
}
