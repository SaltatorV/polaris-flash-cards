package com.saltatorv.polaris.flash.cards.application.blueprint.query;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardLocalizationQueryDto;
import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.saltatorv.polaris.flash.cards.application.blueprint.exception.FlashcardBlueprintExceptionConfiguration.FLASHCARD_BLUEPRINT_NOT_FOUND;

@Service
class GetFlashcardBlueprintDataUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    GetFlashcardBlueprintDataUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    List<FlashcardBlueprintSummaryQueryDto> getFlashcardBlueprintSummariesForCategory(CategoryId categoryId) {
        List<FlashcardBlueprintSnapshot> blueprints = flashcardBlueprintRepository.findAllByCategoryId(categoryId);
        List<FlashcardBlueprintSummaryQueryDto> dtos = new ArrayList<>();

        for (FlashcardBlueprintSnapshot blueprint : blueprints) {
            List<String> localizations = blueprint.getLocalizations()
                    .stream()
                    .map(l ->
                            l.getLocale().getLanguage())
                    .toList();

            dtos.add(new FlashcardBlueprintSummaryQueryDto(blueprint.getFlashcardBlueprintId(),
                    blueprint.getSource(), blueprint.getTags(), localizations));
        }

        return dtos;
    }

    FlashcardBlueprintQueryDto getFlashcardBlueprint(FlashcardBlueprintId id) {

        Optional<FlashcardBlueprintSnapshot> optional = flashcardBlueprintRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ApplicationException(FLASHCARD_BLUEPRINT_NOT_FOUND,
                    "Flashcard blueprint with id: %s does not exist.".formatted(id.getId()));
        }

        FlashcardBlueprintSnapshot blueprint = optional.get();

        List<FlashcardLocalizationQueryDto> localizationDtos = blueprint.getLocalizations()
                .stream()
                .map(this::mapFlashcardLocalizationToDto)
                .toList();


        return new FlashcardBlueprintQueryDto(blueprint.getFlashcardBlueprintId(),
                blueprint.getSource(), blueprint.getTags(), localizationDtos);
    }

    private FlashcardLocalizationQueryDto mapFlashcardLocalizationToDto(FlashcardLocalization localization) {
        return new FlashcardLocalizationQueryDto(localization.getLocale(),
                localization.getContent().getQuestion(),
                localization.getContent().getAnswer());
    }

}
