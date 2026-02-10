package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Optional;

public interface FlashcardBlueprintRepository {

    Optional<FlashcardBlueprintSnapshot> findById(FlashcardBlueprintId id);

    FlashcardBlueprintSnapshot save(FlashcardBlueprintSnapshot flashcardBlueprint);

    List<FlashcardBlueprintSnapshot> findByIds(List<FlashcardBlueprintId> ids);
}
