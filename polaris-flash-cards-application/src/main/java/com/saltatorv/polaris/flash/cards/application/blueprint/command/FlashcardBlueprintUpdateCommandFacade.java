package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardBlueprintUpdateCommandFacade {
    public void updateBlueprintMetadata(String blueprintId,
                                        FlashcardBlueprintMetadataUpdateDto dto) {
    }

    public void updateLocalization(String blueprintId, String locale,
                                   FlashcardLocalizationUpdateDto dto) {
    }
}
