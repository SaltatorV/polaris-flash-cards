package com.saltatorv.polaris.flash.cards.container.fixture;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;
import com.saltatorv.polaris.flash.cards.container.builder.FlashcardBlueprintCreationDtoBuilder;

public class FlashcardBlueprintCommandFixture {

    public static FlashcardBlueprintCreationDtoBuilder defaultBlueprint(String categoryId) {
        return new FlashcardBlueprintCreationDtoBuilder()
                .withCategoryId(categoryId)
                .withSource("source")
                .withTags("tag-1", "tag-2")
                .defineLocalization()
                .withLocale(Locale.EN)
                .withQuestion("question-1")
                .withAnswer("answer-1")
                .createLocalization();
    }
}
