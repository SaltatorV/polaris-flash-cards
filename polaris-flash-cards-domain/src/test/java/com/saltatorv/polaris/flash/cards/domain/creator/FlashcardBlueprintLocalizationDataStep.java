package com.saltatorv.polaris.flash.cards.domain.creator;

public interface FlashcardBlueprintLocalizationDataStep {
    FlashcardBlueprintLocalizationDataStep forLanguage(String language);
    FlashcardBlueprintLocalizationDataStep attachQuestion(String question);
    FlashcardBlueprintLocalizationDataStep withAnswer(String answer);
    FlashcardBlueprintLocalizationStep done();
}
