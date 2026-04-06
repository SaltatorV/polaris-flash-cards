package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class FlashcardBlueprintIdCacheImpl implements FlashcardBlueprintIdCache {
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;

    private List<FlashcardBlueprintId> ids;

    public FlashcardBlueprintIdCacheImpl(FlashcardBlueprintRepository flashcardBlueprintRepository) {
        ids = new ArrayList<>();
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        invalidate();
    }

    @Override
    public List<FlashcardBlueprintId> getAll() {
        return new ArrayList<>(ids);
    }

    @Override
    public void invalidate() {
        ids.clear();
        List<FlashcardBlueprintSnapshot> snapshots = flashcardBlueprintRepository.findAll();
        ids = snapshots
                .stream()
                .map(snapshot -> new FlashcardBlueprintId(snapshot.getFlashcardBlueprintId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
