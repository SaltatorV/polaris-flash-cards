package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintLocalizationAlreadyExistsDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintLocalizationDoNotExistsDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardBlueprintWithoutLocalizationDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Locale;

public class FlashcardBlueprint {
    private final FlashcardBlueprintId flashcardBlueprintId;
    private final List<FlashcardLocalization> localizations;
    private final FlashcardMetadata metadata;

    public FlashcardBlueprint(List<FlashcardLocalization> localizations, FlashcardMetadata metadata) {
        if (localizations.isEmpty()) {
            throw new FlashcardBlueprintWithoutLocalizationDomainException();
        }

        this.flashcardBlueprintId = FlashcardBlueprintId.generate();
        this.localizations = localizations;
        this.metadata = metadata;
    }

    private FlashcardBlueprint(FlashcardBlueprintId flashcardBlueprintId, List<FlashcardLocalization> localizations, FlashcardMetadata metadata) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.localizations = localizations;
        this.metadata = metadata;
    }

    public static FlashcardBlueprint restore(FlashcardBlueprintSnapshot snapshot) {

        FlashcardMetadata flashcardMetadata = new FlashcardMetadata(snapshot.getSource(),
                snapshot.getTags());

        return new FlashcardBlueprint(new FlashcardBlueprintId(snapshot.getFlashcardBlueprintId()),
                snapshot.getLocalizations(),
                flashcardMetadata);
    }

    public FlashcardBlueprintSnapshot generateSnapshot() {
        return new FlashcardBlueprintSnapshot(flashcardBlueprintId.getId(), localizations, metadata.getSource(), metadata.getTags());
    }

    public Flashcard createFlashcard(Locale locale) {
        FlashcardLocalization chosenLocalization = findLocalizationOrThrow(locale.toLanguageTag());
        return new Flashcard(flashcardBlueprintId.getId(), chosenLocalization.getQuestion(), chosenLocalization.getAnswer());
    }

    public FlashcardMetadata getMetadata() {
        return metadata;
    }

    public void addNewLocalization(FlashcardLocalization newLocalization) {
        FlashcardLocalization found = findLocalization(newLocalization.getLocale().toLanguageTag());

        if (found != null) {
            throw new FlashcardBlueprintLocalizationAlreadyExistsDomainException(newLocalization.getLocale().toLanguageTag());
        }

        localizations.add(newLocalization);
    }

    public void removeLocalization(String locale) {

        FlashcardLocalization found = findLocalizationOrThrow(locale);

        if (localizations.size() == 1) {
            throw new FlashcardBlueprintWithoutLocalizationDomainException();
        }

        localizations.remove(found);
    }

    public void updateLocalization(String locale, String question, String answer) {
        FlashcardLocalization found = findLocalizationOrThrow(locale);

        localizations.remove(found);

        localizations.add(new FlashcardLocalization(Locale.forLanguageTag(locale), question, answer));
    }

    private FlashcardLocalization findLocalizationOrThrow(String locale) {

        FlashcardLocalization found = findLocalization(locale);

        if (found == null) {
            throw new FlashcardBlueprintLocalizationDoNotExistsDomainException(locale);
        }

        return found;

    }

    private FlashcardLocalization findLocalization(String locale) {
        return localizations.stream()
                .filter(localization ->
                        localization.getLocale().equals(Locale.forLanguageTag(locale)))
                .findFirst()
                .orElse(null);
    }
}
