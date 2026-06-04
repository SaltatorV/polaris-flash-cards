package com.saltatorv.polaris.flash.cards.application.query.blueprint.view;

import com.saltatorv.polaris.flash.cards.application.query.blueprint.dto.FlashcardBlueprintQueryDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardBlueprintViewFacade {
    private final GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase;

    public FlashcardBlueprintViewFacade(GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase) {
        this.getFlashcardBlueprintDataUseCase = getFlashcardBlueprintDataUseCase;
    }

    public FlashcardBlueprintQueryDto getFlashcardBlueprintData() {
        return null;
    }
}
