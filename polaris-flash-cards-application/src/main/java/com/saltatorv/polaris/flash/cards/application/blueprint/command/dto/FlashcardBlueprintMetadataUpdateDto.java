package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class FlashcardBlueprintMetadataUpdateDto {
    @NotEmpty(message = "Source cannot be empty")
    @Size(min = 3, max = 100, message = "Source must be between {min} and {max} characters")
    private String source;
    @NotEmpty(message = "Tags cannot be empty")
    @Size(min = 1, message = "At least one tag is required")
    private Set<@NotBlank(message = "Tag cannot be empty")
                @Size(min = 2, max = 15, message = "Tag must be between {min} and {max} characters")
            String> tags;

    public FlashcardBlueprintMetadataUpdateDto() {
    }

    public FlashcardBlueprintMetadataUpdateDto(String source, Set<String> tags) {
        this.source = source;
        this.tags = tags;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }
}
