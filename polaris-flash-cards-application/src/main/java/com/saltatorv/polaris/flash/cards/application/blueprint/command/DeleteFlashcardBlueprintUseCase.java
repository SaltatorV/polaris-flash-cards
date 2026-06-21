package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Service;

@Service
class DeleteFlashcardBlueprintUseCase {

    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    DeleteFlashcardBlueprintUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    void deleteBlueprint(String blueprintId) {
        flashcardBlueprintRepository.deleteById(new FlashcardBlueprintId(blueprintId));
    }
}
