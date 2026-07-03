package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardContent;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;
import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintWithoutLocalizationDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

import static com.saltatorv.polaris.flash.cards.application.blueprint.exception.FlashcardBlueprintExceptionConfiguration.FLASHCARD_BLUEPRINT_NOT_FOUND;

@Service
class UpdateBlueprintLocalizationUseCase {

    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    UpdateBlueprintLocalizationUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    void updateLocalization(String blueprintId, String locale,
                            FlashcardLocalizationUpdateDto dto) {
        Optional<FlashcardBlueprintSnapshot> optional = flashcardBlueprintRepository.findById(new FlashcardBlueprintId(blueprintId));

        if (optional.isEmpty()) {
            throw new ApplicationException(FLASHCARD_BLUEPRINT_NOT_FOUND,
                    "Flashcard blueprint with id: %s does not exist.".formatted(blueprintId));
        }

        FlashcardBlueprint blueprint = FlashcardBlueprint.restore(optional.get());

        try {
            blueprint.removeLocalization(locale);
        }
        catch (FlashcardBlueprintWithoutLocalizationDomainException ex) {
            // swallow because in next operation it will be added
        }

        blueprint.addNewLocalization(new FlashcardLocalization(Locale.of(locale),
                new FlashcardContent(dto.getQuestion(), dto.getAnswer())));

        flashcardBlueprintRepository.save(blueprint.generateSnapshot());
    }
}
