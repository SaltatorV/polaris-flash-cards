package com.saltatorv.polaris.flash.cards.application.blueprint.query;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardBlueprintViewFacade {
    private final GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase;

    public FlashcardBlueprintViewFacade(GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase) {
        this.getFlashcardBlueprintDataUseCase = getFlashcardBlueprintDataUseCase;
    }

    public FlashcardBlueprintSummaryQueryDto getFlashcardBlueprintData() {
        return null;
    }
}
