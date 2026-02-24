package com.saltatorv.polaris.flash.cards.web;

import com.saltatorv.polaris.flash.cards.application.command.blueprint.AddFlashcardBlueprintUseCase;
import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcard/blueprints")
public class FlashcardBlueprintController {

    private final AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase;

    public FlashcardBlueprintController(AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase) {
        this.addFlashcardBlueprintUseCase = addFlashcardBlueprintUseCase;
    }

    @PostMapping
    public void createFlashcardBlueprints(@RequestBody List<FlashcardBlueprintDataDto> requests) {
        addFlashcardBlueprintUseCase.addFlashcardBlueprints(requests);
    }
}
