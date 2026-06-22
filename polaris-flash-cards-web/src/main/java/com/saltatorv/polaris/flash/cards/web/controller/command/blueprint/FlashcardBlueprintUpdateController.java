package com.saltatorv.polaris.flash.cards.web.controller.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.FlashcardBlueprintUpdateCommandFacade;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardBlueprintUpdateController {
    private static final String BASE_ENDPOINT = "/flashcard/blueprint";

    public static final String FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT = BASE_ENDPOINT + "/{id}/updateMetadata";
    public static final String FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT = BASE_ENDPOINT + "/{id}/localization/{locale}/update";

    private final FlashcardBlueprintUpdateCommandFacade flashcardBlueprintUpdateCommandFacade;

    public FlashcardBlueprintUpdateController(FlashcardBlueprintUpdateCommandFacade flashcardBlueprintUpdateCommandFacade) {
        this.flashcardBlueprintUpdateCommandFacade = flashcardBlueprintUpdateCommandFacade;
    }

    @PatchMapping(FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT)
    public void updateFlashcardBlueprintMetadata(@PathVariable("id") String id,
                                                 @RequestBody FlashcardBlueprintMetadataUpdateDto dto) {
        flashcardBlueprintUpdateCommandFacade.updateBlueprintMetadata(id, dto);
    }

    @PatchMapping(FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT)
    public void updateFlashcardLocalizations(@PathVariable("id") String id, @PathVariable("locale") String locale,
                                             @RequestBody FlashcardLocalizationUpdateDto dto) {
        flashcardBlueprintUpdateCommandFacade.updateLocalization(id, locale, dto);
    }

}
