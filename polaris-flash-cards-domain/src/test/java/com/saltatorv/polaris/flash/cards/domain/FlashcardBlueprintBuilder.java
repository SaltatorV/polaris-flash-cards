package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintBuilderSourceStep;
import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintBuilderTagsStep;
import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintLocalizationBuilderDataStep;
import com.saltatorv.polaris.flash.cards.domain.builder.step.flashcard.blueprint.FlashcardBlueprintLocalizationBuilderStep;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class FlashcardBlueprintBuilder implements FlashcardBlueprintBuilderSourceStep, FlashcardBlueprintBuilderTagsStep, FlashcardBlueprintLocalizationBuilderStep, FlashcardBlueprintLocalizationBuilderDataStep {
    private String source;
    private Set<String> tags;

    private List<String> languages;
    private List<String> questions;
    private List<String> answers;

    private FlashcardBlueprintBuilder() {
        languages = new ArrayList<>();
        questions = new ArrayList<>();
        answers = new ArrayList<>();
    }

    public static FlashcardBlueprintBuilderSourceStep buildFlashcardBlueprint() {
        return new FlashcardBlueprintBuilder();
    }

    @Override
    public FlashcardBlueprintBuilderTagsStep fromSource(String source) {
        this.source = source;
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderStep withTags(String... tags) {
        this.tags = Set.of(tags);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderDataStep forLanguage(String language) {
        this.languages.add(language);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderDataStep attachQuestion(String question) {
        this.questions.add(question);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderDataStep withAnswer(String answer) {
        this.answers.add(answer);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderStep done() {
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationBuilderDataStep defineLocalization() {
        return this;
    }

    @Override
    public FlashcardBlueprint create() {
        List<FlashcardLocalization> localizations = new ArrayList<>();
        for (int i = 0; i < languages.size(); i++) {
            localizations.add(
                    new FlashcardLocalization(
                            Locale.of(languages.get(i)),
                            new FlashcardContent(
                                    questions.get(i),
                                    answers.get(i)))
            );
        }

        FlashcardMetadata metadata = new FlashcardMetadata(source, tags);

        return new FlashcardBlueprint(CategoryId.generate(), localizations, metadata);
    }
}
