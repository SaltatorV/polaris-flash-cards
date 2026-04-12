package com.saltatorv.polaris.flash.cards.application.query.blueprint.view;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import org.springframework.stereotype.Service;

@Service
class GetFlashcardBlueprintDataUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    GetFlashcardBlueprintDataUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }


}
