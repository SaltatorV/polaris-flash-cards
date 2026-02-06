package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReview;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GenerateReviewUseCase {

    private final FlashcardReviewRepository flashcardReviewRepository;
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    public GenerateReviewUseCase(FlashcardReviewRepository flashcardReviewRepository, FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardReviewRepository = flashcardReviewRepository;
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    public FlashcardReviewId generateReview(List<FlashcardBlueprintId> flashcardIds) {
        List<FlashcardBlueprint> flashcardBlueprints = flashcardBlueprintRepository.findByIds(flashcardIds);
        FlashcardReview toSave = new FlashcardReview(flashcardBlueprints);
        FlashcardReview savedReview = flashcardReviewRepository.save(toSave);

        return savedReview.getId();
    }

    public FlashcardReviewId generateRandomReview(int reviewSize) {
        List<FlashcardBlueprintId> cachedIds = flashcardBlueprintIdCache.getAll();

        if (cachedIds.size() < reviewSize) {
            return generateReview(cachedIds);
        }

        List<FlashcardBlueprintId> reservoir = fillUpWithFirstNElements(cachedIds, reviewSize);
        reservoirSampling(reservoir, cachedIds, reviewSize);

        return generateReview(reservoir);
    }

    private List<FlashcardBlueprintId> fillUpWithFirstNElements(List<FlashcardBlueprintId> cachedIds,
                                                                int reviewSize) {
        List<FlashcardBlueprintId> reservoir = new ArrayList<>(reviewSize);

        for (int i = 0; i < reviewSize; i++) {
            reservoir.add(cachedIds.get(i));
        }

        return reservoir;
    }

    private List<FlashcardBlueprintId> reservoirSampling(List<FlashcardBlueprintId> reservoir,
                                                         List<FlashcardBlueprintId> cachedIds,
                                                         int reviewSize) {
        Random random = new Random();

        for (int indexAfterNElements = reviewSize; indexAfterNElements < cachedIds.size(); indexAfterNElements++) {
            int replaceIndex = random.nextInt(indexAfterNElements + 1);
            if (replaceIndex < reviewSize) {
                reservoir.set(replaceIndex, cachedIds.get(indexAfterNElements));
            }
        }

        return reservoir;
    }
}
