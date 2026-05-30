package com.saltatorv.polaris.flash.cards.domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

import static com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintBuilder.buildFlashcardBlueprint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlashcardBlueprintTest {

    @Test
    void testShouldCreateFlashcardBlueprintWithSingleLocalization() {
        // given

        // when
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        // then
        assertBlueprintIsFrom(blueprint, "Java OCP");
        assertBlueprintContainTags(blueprint, "JAVA", "OCP", "Basic");
        assertBlueprintHaveLocalizationFor(blueprint, "EN", "Question?", "Answer");
    }


    @Test
    void testShouldCreateFlashcardBlueprintWithMultipleLocalizations() {
        // when

        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .defineLocalization()
                .forLanguage("PL")
                .attachQuestion("Pytanie?")
                .withAnswer("Odpowiedz")
                .done()
                .create();

        // then
        assertBlueprintIsFrom(blueprint, "Java OCP");
        assertBlueprintContainTags(blueprint, "JAVA", "OCP", "Basic");
        assertBlueprintHaveLocalizationFor(blueprint, "EN", "Question?", "Answer");
        assertBlueprintHaveLocalizationFor(blueprint, "PL", "Pytanie?", "Odpowiedz");
    }

    @Test
    void testShouldThrowExceptionWhenCreateFlashcardBlueprintWithoutLocalizations() {
        // given
        var builder = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .done();

        // when
        assertThrows(RuntimeException.class, builder::create);
    }

    @Test
    void testShouldAllowToAddAdditionalLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        var newLocalization = new FlashcardLocalization(Locale.of("PL"), "Pytanie?", "Odpowiedz?");

        // when
        blueprint.addNewLocalization(newLocalization);

        // then
        assertBlueprintHaveLocalizationFor(blueprint, "EN", "Question?", "Answer");
        assertBlueprintHaveLocalizationFor(blueprint, "PL", "Pytanie?", "Odpowiedz");
    }

    @Test
    void testShouldAllowToAddMultipleAdditionalLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        var first = new FlashcardLocalization(Locale.of("PL"), "Pytanie?", "Odpowiedz?");
        var second = new FlashcardLocalization(Locale.of("DE"), "Frage?", "Antwort?");
        var third = new FlashcardLocalization(Locale.of("FR"), "Question?", "Reponse?");

        // when
        blueprint.addNewLocalization(first);
        blueprint.addNewLocalization(second);
        blueprint.addNewLocalization(third);

        // then
        assertBlueprintHaveLocalizationFor(blueprint, "EN", "Question?", "Answer");
        assertBlueprintHaveLocalizationFor(blueprint, "PL", "Pytanie?", "Odpowiedz");
        assertBlueprintHaveLocalizationFor(blueprint, "DE", "Frage?", "Antwort");
        assertBlueprintHaveLocalizationFor(blueprint, "FR", "Question?", "Reponse");
    }

    @Test
    public void testShouldThrowExceptionWhenTryToAddLocalizationWithSameLocale() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        var newLocalization = new FlashcardLocalization(Locale.of("EN"), "Question?", "Answer?");

        //when
        assertThrows(RuntimeException.class, blueprint.addNewLocalization);
    }

    @Test
    void testShouldAllowToRemoveLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .defineLocalization()
                .forLanguage("PL")
                .attachQuestion("Pytanie?")
                .withAnswer("Odpowiedz")
                .done()
                .create();

        //when
        blueprint.removeLocalization("PL");

        //then
        assertBlueprintHaveLocalizationFor(blueprint, "EN", "Question?", "Answer");
        assertBlueprintDoNotHaveLocalizationFor(blueprint, "PL");
    }

    @Test
    void testShouldNotAllowToRemoveSameLocalizationTwice() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .defineLocalization()
                .forLanguage("PL")
                .attachQuestion("Pytanie?")
                .withAnswer("Odpowiedz")
                .done()
                .create();

        blueprint.removeLocalization("PL");

        //when
        assertThrows(RuntimeException.class, blueprint.removeLocalization("PL"));
    }

    @Test
    public void testShouldNotAllowToRemoveLastLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        //when
        assertThrows(RuntimeException.class, blueprint.removeLocalization("EN"));
    }

    @Test
    void testShouldAllowToUpdateLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        // when
        blueprint.updateLocalization("EN", "Question2?", "Answer");

        // then
    }

    @Test
    public void testShouldNotAllowToUpdateNotExistingLocalization() {
        // given
        var blueprint = buildFlashcardBlueprint()
                .fromSource("Java OCP")
                .withTags("JAVA", "OCP", "Basic")
                .defineLocalization()
                .forLanguage("EN")
                .attachQuestion("Question?")
                .withAnswer("Answer")
                .done()
                .create();

        // when
        assertThrows(RuntimeException.class, blueprint.updateLocalization("PL", "Pytanie?", "Odpowiedz"));
    }

    private void assertBlueprintIsFrom(FlashcardBlueprint blueprint, String source) {
        assertEquals(blueprint.generateSnapshot().getSource(), source);
    }

    private void assertBlueprintContainTags(FlashcardBlueprint blueprint, String... tags) {
        assertEquals(blueprint.generateSnapshot().getTags(), Set.of(tags));
    }

    private void assertBlueprintHaveLocalizationFor(FlashcardBlueprint blueprint, String locale, String question, String answer) {
        FlashcardBlueprintSnapshot snapshot = blueprint.generateSnapshot();

        snapshot.getLocalizations().stream()
                .filter(localization ->
                        localization.getLocale().equals(Locale.of(locale)))
                .findFirst()
                .ifPresent(localization -> {
                    assertEquals(localization.getQuestion(), question);
                    assertEquals(localization.getAnswer(), answer);
                });
    }

    private void assertBlueprintDoNotHaveLocalizationFor(FlashcardBlueprint blueprint, String locale) {
        FlashcardBlueprintSnapshot snapshot = blueprint.generateSnapshot();
        snapshot.getLocalizations().stream()
                .filter(localization ->
                        localization.getLocale().equals(Locale.of(locale)))
                .findFirst()
                .ifPresent(localization -> {
                    throw new RuntimeException("Localization should not be present");
                });
    }
}
