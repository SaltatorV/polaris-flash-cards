package com.saltatorv.polaris.flash.cards.web.controller.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.FlashcardBlueprintDeleteCommandFacade;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlueprintDeletionController {
    private static final String BASE_ENDPOINT = "/flashcard/blueprint";

    public static final String FLASHCARD_BLUEPRINT_DELETE_ENDPOINT = BASE_ENDPOINT + "/{id}/delete";
    public static final String FLASHCARD_BLUEPRINT_LOCALIZATIONS_DELETE_ENDPOINT = BASE_ENDPOINT + "/{id}/localizations/delete";

    private final FlashcardBlueprintDeleteCommandFacade flashcardBlueprintDeleteCommandFacade;

    public FlashcardBlueprintDeletionController(FlashcardBlueprintDeleteCommandFacade flashcardBlueprintDeleteCommandFacade) {
        this.flashcardBlueprintDeleteCommandFacade = flashcardBlueprintDeleteCommandFacade;
    }

    @DeleteMapping(FLASHCARD_BLUEPRINT_DELETE_ENDPOINT)
    public void deleteFlashcardBlueprint(@PathVariable("id") String id) {
        flashcardBlueprintDeleteCommandFacade.deleteBlueprintById(id);
    }

    @DeleteMapping(FLASHCARD_BLUEPRINT_LOCALIZATIONS_DELETE_ENDPOINT)
    public void deleteLocalizations(@PathVariable("id") String id,
                                    List<FlashcardBlueprintLocalizationDeleteDto> dtos) {
        flashcardBlueprintDeleteCommandFacade.deleteBlueprintLocalizations(id, dtos);
    }
}
