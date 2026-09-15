package com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;

public interface FlashcardBlueprintLocalizationBuilderStep {
    FlashcardBlueprintLocalizationBuilderDataStep defineLocalization();
    FlashcardBlueprint create();
}
