package com.saltatorv.polaris.flash.cards.application.command;

import com.saltatorv.polaris.flash.cards.application.FlashcardBlueprintIdCache;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GenerateReviewUseCaseTest {

    @Mock
    private FlashcardReviewRepository flashcardReviewRepository;
    @Mock
    private FlashcardBlueprintRepository flashcardBlueprintRepository;
    @Mock
    private FlashcardBlueprintIdCache flashcardBlueprintIdCache;

    @InjectMocks
    private GenerateReviewUseCase generateReviewUseCase;


}
