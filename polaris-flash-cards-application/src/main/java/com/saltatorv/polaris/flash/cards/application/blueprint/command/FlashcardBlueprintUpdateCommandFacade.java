package com.saltatorv.polaris.flash.cards.application.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardBlueprintUpdateCommandFacade {

    private final UpdateBlueprintMetadataUseCase updateBlueprintMetadataUseCase;
    private final UpdateBlueprintLocalizationUseCase updateBlueprintLocalizationUseCase;

    public FlashcardBlueprintUpdateCommandFacade(UpdateBlueprintMetadataUseCase updateBlueprintMetadataUseCase, UpdateBlueprintLocalizationUseCase updateBlueprintLocalizationUseCase) {
        this.updateBlueprintMetadataUseCase = updateBlueprintMetadataUseCase;
        this.updateBlueprintLocalizationUseCase = updateBlueprintLocalizationUseCase;
    }

    public void updateBlueprintMetadata(String blueprintId,
                                        FlashcardBlueprintMetadataUpdateDto dto) {
        updateBlueprintMetadataUseCase.updateBlueprintMetadata(blueprintId, dto);
    }

    public void updateLocalization(String blueprintId, String locale,
                                   FlashcardLocalizationUpdateDto dto) {
        updateBlueprintLocalizationUseCase.updateLocalization(blueprintId, locale, dto);
    }
}
