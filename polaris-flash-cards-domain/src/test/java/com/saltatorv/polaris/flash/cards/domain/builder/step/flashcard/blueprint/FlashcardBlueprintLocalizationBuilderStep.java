package com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;

public interface FlashcardBlueprintLocalizationBuilderStep {
    FlashcardBlueprintLocalizationBuilderDataStep defineLocalization();
    FlashcardBlueprintLocalizationBuilderStep defineLocalization(FlashcardLocalization localization);
    FlashcardBlueprint create();
}
