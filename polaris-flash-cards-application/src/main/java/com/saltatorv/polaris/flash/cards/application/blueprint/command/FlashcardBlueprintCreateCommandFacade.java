package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardBlueprintCreateCommandFacade {

    private final CreateFlashcardBlueprintUseCase createFlashcardBlueprintUseCase;

    public FlashcardBlueprintCreateCommandFacade(CreateFlashcardBlueprintUseCase createFlashcardBlueprintUseCase) {
        this.createFlashcardBlueprintUseCase = createFlashcardBlueprintUseCase;
    }

    public void addFlashcardBlueprints(List<FlashcardBlueprintCreateDto> dtos) {
        createFlashcardBlueprintUseCase.addFlashcardBlueprints(dtos);
    }
}
