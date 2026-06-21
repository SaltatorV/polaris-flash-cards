package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardBlueprintDeleteCommandFacade {

    private final DeleteFlashcardBlueprintUseCase deleteFlashcardBlueprintUseCase;
    private final DeleteFlashcardLocalizationUseCase deleteFlashcardLocalizationUseCase;

    public FlashcardBlueprintDeleteCommandFacade(DeleteFlashcardBlueprintUseCase deleteFlashcardBlueprintUseCase,
                                                 DeleteFlashcardLocalizationUseCase deleteFlashcardLocalizationUseCase) {
        this.deleteFlashcardBlueprintUseCase = deleteFlashcardBlueprintUseCase;
        this.deleteFlashcardLocalizationUseCase = deleteFlashcardLocalizationUseCase;
    }

    public void deleteBlueprintById(String blueprintId) {
        deleteFlashcardBlueprintUseCase.deleteBlueprint(blueprintId);
    }

    public void deleteBlueprintLocalizations(String blueprintId, List<FlashcardBlueprintLocalizationDeleteDto> dtos) {
        deleteFlashcardLocalizationUseCase.deleteLocalizations(blueprintId, dtos);
    }
}
