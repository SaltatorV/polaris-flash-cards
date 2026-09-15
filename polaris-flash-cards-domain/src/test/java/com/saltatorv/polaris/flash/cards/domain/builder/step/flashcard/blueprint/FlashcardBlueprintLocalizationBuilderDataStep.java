package com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint;

public interface FlashcardBlueprintLocalizationBuilderDataStep {
    FlashcardBlueprintLocalizationBuilderDataStep forLanguage(String language);
    FlashcardBlueprintLocalizationBuilderDataStep attachQuestion(String question);
    FlashcardBlueprintLocalizationBuilderDataStep withAnswer(String answer);
    FlashcardBlueprintLocalizationBuilderStep done();
}
