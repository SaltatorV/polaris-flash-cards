package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintWithoutLocalizationDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.saltatorv.polaris.flash.cards.application.blueprint.exception.FlashcardBlueprintExceptionConfiguration.FLASHCARD_BLUEPRINT_LAST_LOCALIZATION;
import static com.saltatorv.polaris.flash.cards.application.blueprint.exception.FlashcardBlueprintExceptionConfiguration.FLASHCARD_BLUEPRINT_NOT_FOUND;

@Service
class DeleteFlashcardLocalizationUseCase {

    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    DeleteFlashcardLocalizationUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    void deleteLocalizations(String blueprintId, List<FlashcardBlueprintLocalizationDeleteDto> dtos) {
        Optional<FlashcardBlueprintSnapshot> optional = flashcardBlueprintRepository.findById(new FlashcardBlueprintId(blueprintId));

        if (optional.isEmpty()) {
            throw new ApplicationException(FLASHCARD_BLUEPRINT_NOT_FOUND,
                    "Flashcard blueprint with id: %s does not exist.".formatted(blueprintId));
        }

        FlashcardBlueprint blueprint = FlashcardBlueprint.restore(optional.get());

        dtos.forEach(dto -> {
            try {
                blueprint.removeLocalization(dto.getLocale());
            } catch (FlashcardBlueprintWithoutLocalizationDomainException ex) {
                throw new ApplicationException(FLASHCARD_BLUEPRINT_LAST_LOCALIZATION, ex.getMessage());
            }
        });

        flashcardBlueprintRepository.save(blueprint.generateSnapshot());
    }
}
