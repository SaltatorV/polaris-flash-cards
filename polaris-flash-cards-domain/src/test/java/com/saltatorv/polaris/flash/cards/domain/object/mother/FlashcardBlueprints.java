package com.saltatorv.polaris.flash.cards.domain.object.mother;

import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintLocalizationBuilderStep;

import static com.saltatorv.polaris.flash.cards.domain.builder.FlashcardBlueprintBuilder.buildFlashcardBlueprint;

public class FlashcardBlueprints {

    public static final FlashcardBlueprint withSingleLocalization() {
        var builder = buildBlueprint();
        return buildEnglishLocalization(builder).create();
    }

    public static final FlashcardBlueprint withTwoLocalizations() {
        var builder = buildBlueprint();
        builder = buildEnglishLocalization(builder);
        return buildPolishLocalization(builder).create();
    }

    private static FlashcardBlueprintLocalizationBuilderStep buildBlueprint() {
        return buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic");
    }

    private static FlashcardBlueprintLocalizationBuilderStep buildEnglishLocalization(FlashcardBlueprintLocalizationBuilderStep builder) {
        return builder
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done();
    }

    private static FlashcardBlueprintLocalizationBuilderStep buildPolishLocalization(FlashcardBlueprintLocalizationBuilderStep builder) {
        return builder
                .defineLocalization()
                .forLanguage("PL")
                .attachQuestion("Pytanie?")
                .withAnswer("Odpowiedz")
                .done();
    }

}
