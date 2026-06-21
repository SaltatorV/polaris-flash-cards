package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class DeleteFlashcardLocalizationUseCase {

    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    public DeleteFlashcardLocalizationUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    public void deleteLocalizations(String blueprintId, List<FlashcardBlueprintLocalizationDeleteDto> dtos) {
        Optional<FlashcardBlueprintSnapshot> optional = flashcardBlueprintRepository.findById(new FlashcardBlueprintId(blueprintId));

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Flashcard blueprint with id " + blueprintId + " does not exist.");
        }

        FlashcardBlueprint blueprint = FlashcardBlueprint.restore(optional.get());

        dtos.forEach(dto -> blueprint.removeLocalization(dto.getLocale()));

        flashcardBlueprintRepository.save(blueprint.generateSnapshot());
    }
}
