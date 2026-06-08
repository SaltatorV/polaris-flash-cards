package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardBlueprintCommandFacade {

    private final AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase;

    public FlashcardBlueprintCommandFacade(AddFlashcardBlueprintUseCase addFlashcardBlueprintUseCase) {
        this.addFlashcardBlueprintUseCase = addFlashcardBlueprintUseCase;
    }

    public void addFlashcardBlueprints(List<FlashcardBlueprintCreateDto> dtos) {
        addFlashcardBlueprintUseCase.addFlashcardBlueprints(dtos);
    }
}
