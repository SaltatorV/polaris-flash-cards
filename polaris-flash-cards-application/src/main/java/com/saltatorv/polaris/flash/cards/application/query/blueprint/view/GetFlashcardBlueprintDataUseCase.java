package com.saltatorv.polaris.flash.cards.application.query.blueprint.view;

import com.saltatorv.polaris.flash.cards.application.query.blueprint.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class GetFlashcardBlueprintDataUseCase {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    GetFlashcardBlueprintDataUseCase(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    List<FlashcardBlueprintSummaryQueryDto> getFlashcardBlueprintsForCategory(CategoryId categoryId) {
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

}
