package com.saltatorv.polaris.flash.cards.domain.creator;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;

public interface FlashcardBlueprintLocalizationStep {
    FlashcardBlueprintLocalizationDataStep defineLocalization();
    FlashcardBlueprint create();
}
