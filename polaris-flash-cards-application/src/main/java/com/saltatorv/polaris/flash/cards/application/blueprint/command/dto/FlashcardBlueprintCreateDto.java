package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import com.saltatorv.polaris.flash.cards.application.shared.validation.ValidId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public class FlashcardBlueprintCreateDto {
    @ValidId(message = "Category ID must be a valid UUID")
    @NotEmpty(message = "Category id cannot be empty")
    private String categoryId;
    @NotEmpty(message = "Source cannot be empty")
    @Size(min = 3, max = 100, message = "Source must be between {min} and {max} characters")
    private String source;
    @NotEmpty(message = "Tags cannot be empty")
    @Size(min = 1, message = "At least one tag is required")
    private Set<@NotBlank(message = "Tag cannot be empty")
                @Size(min = 2, max = 15, message = "Tag must be between {min} and {max} characters")
            String> tags;
    @NotEmpty(message = "Localizations cannot be empty")
    @Size(min = 1, message = "At least one localization is required")
    private List<@Valid FlashcardLocalizationCreateDto> localizations;

    public FlashcardBlueprintCreateDto(String categoryId, String source,
                                       Set<String> tags, List<FlashcardLocalizationCreateDto> localizations) {
        this.categoryId = categoryId;
        this.source = source;
        this.tags = tags;
        this.localizations = localizations;
    }

    public FlashcardBlueprintCreateDto() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }

    public List<FlashcardLocalizationCreateDto> getLocalizations() {
        return localizations;
    }
}
