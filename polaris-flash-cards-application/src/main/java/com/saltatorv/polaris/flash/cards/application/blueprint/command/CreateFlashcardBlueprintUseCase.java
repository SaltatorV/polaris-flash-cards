package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintWithoutLocalizationDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.saltatorv.polaris.flash.cards.application.blueprint.command.exception.FlashcardBlueprintExceptionConfiguration.FLASHCARD_BLUEPRINT_HAS_NO_LOCALIZATION;

@Service
class CreateFlashcardBlueprintUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    CreateFlashcardBlueprintUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    void addFlashcardBlueprints(List<FlashcardBlueprintCreateDto> dtos) {
        for (FlashcardBlueprintCreateDto dto : dtos) {

            dto.getLocalizations().forEach(l -> System.out.println(l.getLocale() + " " + l.getQuestion() + " " + l.getAnswer()));
            CategoryId categoryId = new CategoryId(dto.getCategoryId());
            List<FlashcardLocalization> localizations = dto.getLocalizations()
                    .stream()
                    .map(this::mapToFlashcardLocalization)
                    .toList();

            try {
                FlashcardBlueprint blueprint = new FlashcardBlueprint(categoryId,
                        localizations,
                        new FlashcardMetadata(dto.getSource(), dto.getTags()));

                flashcardBlueprintRepository.save(blueprint.generateSnapshot());

            } catch (FlashcardBlueprintWithoutLocalizationDomainException ex) {
                throw new ApplicationException(FLASHCARD_BLUEPRINT_HAS_NO_LOCALIZATION, ex.getMessage());
            }
        }

        flashcardBlueprintIdCache.invalidate();
    }

    private FlashcardLocalization mapToFlashcardLocalization(FlashcardLocalizationCreateDto dto) {
        return new FlashcardLocalization(Locale.forLanguageTag(dto.getLocale()),
                new FlashcardContent(dto.getQuestion(), dto.getAnswer()));
    }
}
