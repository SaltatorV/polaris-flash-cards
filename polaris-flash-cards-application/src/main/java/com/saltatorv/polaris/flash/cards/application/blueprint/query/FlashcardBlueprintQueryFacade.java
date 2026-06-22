package com.saltatorv.polaris.flash.cards.application.blueprint.query;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardBlueprintQueryFacade {
    private final GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase;

    public FlashcardBlueprintQueryFacade(GetFlashcardBlueprintDataUseCase getFlashcardBlueprintDataUseCase) {
        this.getFlashcardBlueprintDataUseCase = getFlashcardBlueprintDataUseCase;
    }

    public List<FlashcardBlueprintSummaryQueryDto> getFlashcardBlueprintSummariesForCategory(String categoryId) {
        return getFlashcardBlueprintDataUseCase.getFlashcardBlueprintSummariesForCategory(new CategoryId(categoryId));
    }

    public FlashcardBlueprintQueryDto getFlashcardBlueprint(String blueprintId) {
        return getFlashcardBlueprintDataUseCase.getFlashcardBlueprint(new FlashcardBlueprintId(blueprintId));
    }
}
