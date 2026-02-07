package com.saltatorv.polaris.flash.cards.application.query;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;

public class GetFlashcardBlueprintDataUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    public GetFlashcardBlueprintDataUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }


}
