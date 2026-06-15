package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AddFlashcardBlueprintUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    AddFlashcardBlueprintUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    public void addFlashcardBlueprints(List<FlashcardBlueprintCreateDto> dtos) {
        for (FlashcardBlueprintCreateDto dto : dtos) {
            System.out.println(dto.getCategoryId() + " " + dto.getSource() + " " + dto.getTags());
            dto.getLocalizations().forEach(l -> System.out.println(l.getLocale() + " " + l.getQuestion() + " " + l.getAnswer()));
            CategoryId categoryId = new CategoryId(dto.getCategoryId());
            List<FlashcardLocalization> localizations = dto.getLocalizations()
                    .stream()
                    .map(this::mapToFlashcardLocalization)
                    .toList();

            FlashcardBlueprint blueprint = new FlashcardBlueprint(categoryId,
                    localizations,
                    new FlashcardMetadata(dto.getSource(), dto.getTags()));

            flashcardBlueprintRepository.save(blueprint.generateSnapshot());
        }

        flashcardBlueprintIdCache.invalidate();
    }

    private FlashcardLocalization mapToFlashcardLocalization(FlashcardLocalizationCreateDto dto) {
        return new FlashcardLocalization(Locale.forLanguageTag(dto.getLocale()),
                new FlashcardContent(dto.getQuestion(), dto.getAnswer()));
    }
}
