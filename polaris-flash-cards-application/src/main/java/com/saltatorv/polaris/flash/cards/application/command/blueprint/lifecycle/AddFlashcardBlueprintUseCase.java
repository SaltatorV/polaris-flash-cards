package com.saltatorv.polaris.flash.cards.application.command.blueprint.lifecycle;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
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

    public void addFlashcardBlueprints(List<FlashcardBlueprintCreateDto> dtos) {
        for (FlashcardBlueprintCreateDto dto : dtos) {

            CategoryId categoryId = new CategoryId(dto.getCategoryId());
            List<FlashcardLocalization> localizations = dto.getLocalizations()
                    .stream()
                    .map(newLocalization ->
                            new FlashcardLocalization(newLocalization.getLocale(),
                                    newLocalization.getQuestion(),
                                    newLocalization.getAnswer()))
                    .toList();

            FlashcardBlueprint blueprint = new FlashcardBlueprint(categoryId,
                    localizations,
                    new FlashcardMetadata(dto.getSource(), dto.getTags()));

            flashcardBlueprintRepository.save(blueprint.generateSnapshot());
        }

        flashcardBlueprintIdCache.invalidate();
    }
}
