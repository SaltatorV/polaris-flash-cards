package com.saltatorv.polaris.flash.cards.application.review.command.lifecycle;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardReviewSnapshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class GenerateReviewUseCase {

    private final FlashcardReviewRepository flashcardReviewRepository;
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    GenerateReviewUseCase(FlashcardReviewRepository flashcardReviewRepository, FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardReviewRepository = flashcardReviewRepository;
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    FlashcardReviewId generateReview(List<FlashcardBlueprintId> flashcardIds) {
        List<FlashcardBlueprintSnapshot> flashcardBlueprintsSnapshots = flashcardBlueprintRepository.findByIds(flashcardIds);

        List<FlashcardBlueprint> flashcardBlueprints = flashcardBlueprintsSnapshots
                .stream()
                .map(FlashcardBlueprint::restore)
                .toList();

        FlashcardReview toSave = new FlashcardReview(flashcardBlueprints);
        FlashcardReviewSnapshot savedReview = flashcardReviewRepository.save(toSave.generateSnapshot());

        return new FlashcardReviewId(savedReview.getFlashcardReviewId());
    }

    public FlashcardReviewId generateRandomReview(int reviewSize) {
        List<FlashcardBlueprintId> cachedIds = flashcardBlueprintIdCache.getAll();
        if (cachedIds.size() < reviewSize) {
            return generateReview(cachedIds);
        }
        else {
            Collections.shuffle(cachedIds);
            return generateReview(new ArrayList<>(cachedIds.subList(0, reviewSize)));
        }
    }
}
