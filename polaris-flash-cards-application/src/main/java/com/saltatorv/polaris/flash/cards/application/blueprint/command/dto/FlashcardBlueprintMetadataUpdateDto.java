package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import java.util.Set;

public class FlashcardBlueprintMetadataUpdateDto {
    private String source;
    private Set<String> tags;

    public FlashcardBlueprintMetadataUpdateDto() {
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }
}
