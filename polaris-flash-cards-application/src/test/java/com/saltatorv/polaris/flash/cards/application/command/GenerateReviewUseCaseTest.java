package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardMetadata;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

class GenerateReviewUseCaseTest {
    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;
    @Mock
    private FlashcardBlueprintRepository flashcardBlueprintRepository;
    @Mock
    private FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    @InjectMocks
    private GenerateReviewUseCase generateReviewUseCase;


    @Test
    public void testShouldGenerateReview() {

    }

    private List<FlashcardBlueprint> generateFlashcardBlueprints(int size) {
        List<FlashcardBlueprint> blueprints = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            blueprints.add(new FlashcardBlueprint("Question-%s".formatted(i),
                    "Answer-%s".formatted(i),
                    new FlashcardMetadata("Source-%s".formatted(i),
                            List.of("Tag-%s.1".formatted(i), "Tag-%s.2".formatted(i)),
                            java.util.Locale.ENGLISH)));
        }

        return blueprints;
    }

}
