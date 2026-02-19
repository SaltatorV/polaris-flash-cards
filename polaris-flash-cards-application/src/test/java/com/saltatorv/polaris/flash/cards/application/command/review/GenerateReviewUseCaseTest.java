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

import static org.junit.jupiter.api.Assertions.*;
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

        when(flashcardReviewRepository.save(any(FlashcardReviewSnapshot.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when
        FlashcardReviewId reviewId = generateReviewUseCase.generateReview(ids);

        //then
        assertNotNull(reviewId);
        assertEquals(reviewId.getId(), getUUID(reviewId));
    }

    @Test
    public void testShouldThrowExceptionWhenGenerateReviewFromEmptyList() {
        //given
        List<FlashcardBlueprintId> ids = List.of();

        //when & then
        assertThrows(RuntimeException.class, () -> generateReviewUseCase.generateReview(ids));
    }

    @Test
    public void testShouldGenerateRandomReview() {
        //given
        when(flashcardReviewRepository.save(any(FlashcardReviewSnapshot.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(flashcardBlueprintIdCache.getAll())
                .thenReturn(generateFlashcardBlueprintIds(5));

        //when
        FlashcardReviewId reviewId = generateReviewUseCase.generateRandomReview(5);

        //then
        assertNotNull(reviewId);
        assertEquals(reviewId.getId(), getUUID(reviewId));

    }

    @Test
    public void testShouldThrowExceptionWhenGenerateRandomReviewFromEmptyList() {
        //when & then
        assertThrows(RuntimeException.class, () -> generateReviewUseCase.generateRandomReview(0));
    }

    private String getUUID(FlashcardReviewId reviewId) {
        return UUID.fromString(reviewId.getId()).toString();
    }

    private List<FlashcardBlueprintId> getRandomBlueprintIds(int size) {
        Collections.shuffle(blueprints);
        return blueprints.subList(0, size).stream().map(FlashcardBlueprint::getFlashcardBlueprintId).toList();
    }

    private void generateFlashcardBlueprints(int count) {
        blueprints = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            blueprints.add(new FlashcardBlueprint("Question-%s".formatted(i),
                    "Answer-%s".formatted(i),
                    new FlashcardMetadata("Source-%s".formatted(i),
                            List.of("Tag-%s.1".formatted(i), "Tag-%s.2".formatted(i)),
                            java.util.Locale.ENGLISH)));
        }
    }

    private List<FlashcardBlueprintId> generateFlashcardBlueprintIds(int count) {
        generateFlashcardBlueprints(count);
        return blueprints.stream().map(FlashcardBlueprint::getFlashcardBlueprintId).toList();
    }
}
