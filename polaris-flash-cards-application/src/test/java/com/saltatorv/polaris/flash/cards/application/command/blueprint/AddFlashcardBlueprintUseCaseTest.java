package com.saltatorv.polaris.flash.cards.application.command.blueprint;

import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprint;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintSnapshot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddFlashcardBlueprintUseCaseTest {

    private List<FlashcardBlueprint> blueprints;
    private List<FlashcardBlueprintDataDto> blueprintDataDtos;

    @Mock
    private FlashcardBlueprintRepository repository;
    @InjectMocks
    private AddFlashcardBlueprintUseCase useCase;

    @BeforeEach
    public void setUp() {
        blueprints = new ArrayList<>();
        blueprintDataDtos = new ArrayList<>();
    }

    @Test
    public void testShouldSaveFlashcardBlueprintsDtos() {
        //given
        generateFlashcardBlueprintDtos(10);

        //when
        addNewFlashcardBlueprint();

        //then
        assertBlueprintsAreCreatedFromDtos();

    }

    private void addNewFlashcardBlueprint() {
        useCase.addFlashcardBlueprints(blueprintDataDtos);
    }

    private void generateFlashcardBlueprintDtos(int size) {
        blueprintDataDtos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            blueprintDataDtos.add(new FlashcardBlueprintDataDto("Question-%s".formatted(i),
                    "Answer-%s".formatted(i), "Source-%s".formatted(i),
                    List.of("Tag-%s.1".formatted(i), "Tag-%s.2".formatted(i)),
                    "PL"));
        }

        when(repository.save(any(FlashcardBlueprintSnapshot.class))).then(invocation -> {
            blueprints.add(invocation.getArgument(0));
            return invocation.getArgument(0);
        });
    }

    private void assertBlueprintsAreCreatedFromDtos() {
        for (int i = 0; i < blueprints.size(); i++) {
            FlashcardBlueprint blueprint = blueprints.get(i);
            FlashcardBlueprintDataDto blueprintDataDto = blueprintDataDtos.get(i);

            assertEquals(blueprintDataDto.getQuestion(), blueprint.createFlashcard().getQuestion());
            assertEquals(blueprintDataDto.getDefinition(), blueprint.createFlashcard().getDefinition());

            assertEquals(blueprintDataDto.getSource(), blueprint.getMetadata().getSource());
            assertEquals(blueprintDataDto.getTags(), blueprint.getMetadata().getTags());
            assertEquals(blueprintDataDto.getLanguage().getLanguage(), blueprint.getMetadata().getLanguage());
        }
    }

}
