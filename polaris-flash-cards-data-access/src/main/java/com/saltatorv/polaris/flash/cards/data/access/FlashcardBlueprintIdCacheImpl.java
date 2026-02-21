package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class FlashcardBlueprintIdCacheImpl implements FlashcardBlueprintIdCache {

    private final List<FlashcardBlueprintId> ids;

    public FlashcardBlueprintIdCacheImpl(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        List<FlashcardBlueprintSnapshot> snapshots = flashcardBlueprintRepository.findAll();
        ids = snapshots
                .stream()
                .map(snapshot -> new FlashcardBlueprintId(snapshot.getFlashcardBlueprintId()))
                .toList();
    }

    @Override
    public List<FlashcardBlueprintId> getAll() {
        return new ArrayList<>(ids);
    }
}
