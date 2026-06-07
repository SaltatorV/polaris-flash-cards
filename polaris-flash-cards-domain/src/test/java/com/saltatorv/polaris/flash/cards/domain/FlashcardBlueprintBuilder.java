package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.creator.FlashcardBlueprintBuilderSourceStep;
import com.saltatorv.polaris.flash.cards.domain.creator.FlashcardBlueprintBuilderTagsStep;
import com.saltatorv.polaris.flash.cards.domain.creator.FlashcardBlueprintLocalizationDataStep;
import com.saltatorv.polaris.flash.cards.domain.creator.FlashcardBlueprintLocalizationStep;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class FlashcardBlueprintBuilder implements FlashcardBlueprintBuilderSourceStep, FlashcardBlueprintBuilderTagsStep, FlashcardBlueprintLocalizationStep, FlashcardBlueprintLocalizationDataStep {
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

    static FlashcardBlueprintBuilderSourceStep buildFlashcardBlueprint() {
        return new FlashcardBlueprintBuilder();
    }

    @Override
    public FlashcardBlueprintBuilderTagsStep fromSource(String source) {
        this.source = source;
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationStep withTags(String... tags) {
        this.tags = Set.of(tags);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationDataStep forLanguage(String language) {
        this.languages.add(language);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationDataStep attachQuestion(String question) {
        this.questions.add(question);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationDataStep withAnswer(String answer) {
        this.answers.add(answer);
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationStep done() {
        return this;
    }

    @Override
    public FlashcardBlueprintLocalizationDataStep defineLocalization() {
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
