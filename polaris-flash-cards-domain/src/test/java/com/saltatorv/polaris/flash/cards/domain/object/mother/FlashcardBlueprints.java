package com.saltatorv.polaris.flash.cards.domain.object.mother;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintLocalizationBuilderStep;

import static com.saltatorv.polaris.flash.cards.domain.builder.FlashcardBlueprintBuilder.buildFlashcardBlueprint;
import static com.saltatorv.polaris.flash.cards.domain.object.mother.FlashcardLocalizations.englishLocalization;
import static com.saltatorv.polaris.flash.cards.domain.object.mother.FlashcardLocalizations.polishLocalization;

public class FlashcardBlueprints {

    public static final FlashcardBlueprint withSingleLocalization() {
        var builder = buildBlueprint();
        return builder
                .defineLocalization(englishLocalization())
                .create();
    }

    public static final FlashcardBlueprint withTwoLocalizations() {
        var builder = buildBlueprint();
        return builder
                .defineLocalization(englishLocalization())
                .defineLocalization(polishLocalization())
                .create();
    }

    private static FlashcardBlueprintLocalizationBuilderStep buildBlueprint() {
        return buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic");
    }
}
