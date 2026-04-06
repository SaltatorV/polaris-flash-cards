package com.saltatorv.polaris.flash.cards.application.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddFlashcardBlueprintUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    public AddFlashcardBlueprintUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    public void addFlashcardBlueprints(List<FlashcardBlueprintDataDto> dtos) {
        for (FlashcardBlueprintDataDto dto : dtos) {
            FlashcardBlueprint blueprint = new FlashcardBlueprint(dto.getQuestion(), dto.getDefinition(),
                    new FlashcardMetadata(dto.getSource(), dto.getTags(), dto.getLanguage()));

            flashcardBlueprintRepository.save(blueprint.generateSnapshot());
        }

        flashcardBlueprintIdCache.invalidate();
    }
}
