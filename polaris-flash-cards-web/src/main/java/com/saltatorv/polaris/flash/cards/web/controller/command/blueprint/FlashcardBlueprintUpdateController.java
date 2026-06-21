package com.saltatorv.polaris.flash.cards.web.controller.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardBlueprintUpdateController {
    public static final String FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT = "/flashcard/blueprint/{id}/updateMetadata";
    public static final String FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT = "/flashcard/blueprint/{id}/localization/{locale}/update";

    @PatchMapping(FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT)
    public void updateFlashcardBlueprintMetadata(@PathVariable("id") String id,
                                                 @RequestBody FlashcardBlueprintMetadataUpdateDto request) {
    }

    @PatchMapping(FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT)
    public void updateFlashcardLocalizations(@PathVariable("id") String id, @PathVariable("locale") String locale,
                                             @RequestBody FlashcardLocalizationUpdateDto request) {

    }

}
