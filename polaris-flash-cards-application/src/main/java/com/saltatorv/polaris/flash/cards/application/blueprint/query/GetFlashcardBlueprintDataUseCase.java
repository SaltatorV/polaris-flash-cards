package com.saltatorv.polaris.flash.cards.application.blueprint.query;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardLocalizationQueryDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            throw new IllegalArgumentException("Flashcard blueprint with id " + id.getId() + " does not exist.");
        }

        FlashcardBlueprintSnapshot blueprint = optional.get();

        List<FlashcardLocalizationQueryDto> localizationDtos = new ArrayList<>();

        blueprint.getLocalizations()
                .stream()
                .map(localization ->
                        localizationDtos.add(new FlashcardLocalizationQueryDto(localization.getLocale(),
                                localization.getContent().getQuestion(),
                                localization.getContent().getAnswer())));

        return new FlashcardBlueprintQueryDto(blueprint.getFlashcardBlueprintId(),
                blueprint.getSource(), blueprint.getTags(), localizationDtos);
    }

}
