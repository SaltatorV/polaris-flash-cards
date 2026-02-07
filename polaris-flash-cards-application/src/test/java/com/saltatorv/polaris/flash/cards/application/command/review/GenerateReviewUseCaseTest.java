package com.saltatorv.polaris.flash.cards.application.command.review;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.*;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenerateReviewUseCaseTest {
    private List<FlashcardBlueprint> blueprints;
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
        //given
        generateFlashcardBlueprints(20);
        List<FlashcardBlueprintId> ids = getRandomBlueprintIds(10);

        when(flashcardReviewRepository.save(any(FlashcardReview.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        FlashcardReviewId reviewId = generateReviewUseCase.generateReview(ids);

        //then
        assertNotNull(reviewId);
        assertEquals(reviewId.getId(), getUUID(reviewId));
    }

    @Test
    public void testShouldGenerateRandomReview() {
        //given
        when(flashcardReviewRepository.save(any(FlashcardReview.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        FlashcardReviewId reviewId = generateReviewUseCase.generateRandomReview(5);

        //then
        assertNotNull(reviewId);
        assertEquals(reviewId.getId(), getUUID(reviewId));

    }

    private String getUUID(FlashcardReviewId reviewId) {
        return UUID.fromString(reviewId.getId()).toString();
    }

    private List<FlashcardBlueprintId> getRandomBlueprintIds(int size) {
        Collections.shuffle(blueprints);
        return blueprints.subList(0, size).stream().map(FlashcardBlueprint::getReviewId).toList();
    }

    private void generateFlashcardBlueprints(int size) {
        blueprints = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            blueprints.add(new FlashcardBlueprint("Question-%s".formatted(i),
                    "Answer-%s".formatted(i),
                    new FlashcardMetadata("Source-%s".formatted(i),
                            List.of("Tag-%s.1".formatted(i), "Tag-%s.2".formatted(i)),
                            java.util.Locale.ENGLISH)));
        }
    }

}
