package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintCreationEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintDeletionEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.command.FlashcardBlueprintUpdateEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.blueprint.query.FlashcardBlueprintQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.command.CategoryCreationCommandEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.caller.category.query.CategoryQueryEndpointCaller;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import com.saltatorv.polaris.flash.cards.container.model.FlashcardBlueprintCreationDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FlashcardBlueprintE2ETest extends BaseE2ETest {

    // category callers
    CategoryQueryEndpointCaller categoryQueryEndpointCaller;
    CategoryCreationCommandEndpointCaller categoryCreationCommandEndpointCaller;

    // blueprint callers
    FlashcardBlueprintCreationEndpointCaller flashcardBlueprintCreationEndpointCaller;
    FlashcardBlueprintDeletionEndpointCaller flashcardBlueprintDeletionEndpointCaller;
    FlashcardBlueprintUpdateEndpointCaller flashcardBlueprintUpdateEndpointCaller;

    FlashcardBlueprintQueryEndpointCaller flashcardBlueprintQueryEndpointCaller;

    //model
    List<CategoryDto> listOfCategories;

    @BeforeEach
    public void setup() {
        flashcardBlueprintCreationEndpointCaller = FlashcardBlueprintCreationEndpointCaller.build();
        flashcardBlueprintDeletionEndpointCaller = FlashcardBlueprintDeletionEndpointCaller.build();
        flashcardBlueprintUpdateEndpointCaller = FlashcardBlueprintUpdateEndpointCaller.build();
        flashcardBlueprintQueryEndpointCaller = FlashcardBlueprintQueryEndpointCaller.build();

        categoryQueryEndpointCaller = CategoryQueryEndpointCaller.build();
        categoryCreationCommandEndpointCaller = CategoryCreationCommandEndpointCaller.build();
        listOfCategories = new ArrayList<>();
    }

    @Test
    public void testShouldCreateBlueprintWithMultipleLocalizations() {

    }

    @Test
    public void testShouldCreateMultipleBlueprints() {

    }

    @Test
    public void testShouldCreateBlueprintWithSingleLocalizations() {

    }

    @Test
    public void testShouldNotAllowToCreateBlueprintWithoutAtLeastOneLocalization() {

    }

    @Test
    public void testShouldAllowToUpdateBlueprint() {

    }

    @Test
    public void testShouldCreateBlueprintUnderSubCategory() {

    }

    @Test
    public void testShouldNotAllowToCreateBlueprintUnderNonExistingCategory() {

    }

    private FlashcardBlueprintCreationDtoBuilder buildBlueprint() {
        return new FlashcardBlueprintCreationDtoBuilder();
    }


}
