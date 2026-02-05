package com.saltatorv.polaris.flash.cards.application;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;

public interface FlashcardBlueprintIdCache {

    List<FlashcardBlueprintId> getAll();
}
