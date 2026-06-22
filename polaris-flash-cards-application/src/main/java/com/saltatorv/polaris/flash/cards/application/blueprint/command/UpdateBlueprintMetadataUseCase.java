package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UpdateBlueprintMetadataUseCase {

    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    UpdateBlueprintMetadataUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }


    void updateBlueprintMetadata(String blueprintId, FlashcardBlueprintMetadataUpdateDto dto) {
        Optional<FlashcardBlueprintSnapshot> optional = flashcardBlueprintRepository.findById(new FlashcardBlueprintId(blueprintId));

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Flashcard blueprint with id " + blueprintId + " does not exist.");
        }

        FlashcardBlueprint blueprint = FlashcardBlueprint.restore(optional.get());

        blueprint.updateMetadata(new FlashcardMetadata(dto.getSource(), dto.getTags()));

        flashcardBlueprintRepository.save(blueprint.generateSnapshot());
    }
}
