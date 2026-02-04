package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Optional;

public interface FlashcardBlueprintRepository {

    Optional<FlashcardBlueprint> findById(FlashcardBlueprintId id);

    FlashcardBlueprint save(FlashcardBlueprint flashcardBlueprint);

    List<FlashcardBlueprint> findByIds(List<FlashcardBlueprintId> ids);
}
