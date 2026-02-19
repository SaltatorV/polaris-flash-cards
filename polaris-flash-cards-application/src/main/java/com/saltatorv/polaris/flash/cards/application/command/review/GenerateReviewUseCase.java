package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GenerateReviewUseCase {

    private final FlashcardReviewRepository flashcardReviewRepository;
    private final FlashcardBlueprintRepository flashcardBlueprintRepository;
    private final FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    public GenerateReviewUseCase(FlashcardReviewRepository flashcardReviewRepository, FlashcardBlueprintRepository flashcardBlueprintRepository, FlashcardBlueprintIdCache flashcardBlueprintIdCache) {
        this.flashcardReviewRepository = flashcardReviewRepository;
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
        this.flashcardBlueprintIdCache = flashcardBlueprintIdCache;
    }

    public FlashcardReviewId generateReview(List<FlashcardBlueprintId> flashcardIds) {

        if(flashcardIds.isEmpty()){
            throw new IllegalArgumentException("The selected flashcard collection cannot be empty.");
        }

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

    private void reservoirSampling(List<FlashcardBlueprintId> reservoir,
                                   List<FlashcardBlueprintId> cachedIds,
                                   int reviewSize) {
        Random random = new Random();

        for (int indexAfterNElements = reviewSize; indexAfterNElements < cachedIds.size(); indexAfterNElements++) {
            int replaceIndex = random.nextInt(indexAfterNElements + 1);
            if (replaceIndex < reviewSize) {
                reservoir.set(replaceIndex, cachedIds.get(indexAfterNElements));
            }
        }

    }
}
