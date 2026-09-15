package com.saltatorv.polaris.flash.cards.domain.object.mother;

import com.saltatorv.polaris.flash.cards.domain.FlashcardContent;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;

import java.util.Locale;

public class FlashcardLocalizations {

    public static final FlashcardLocalization englishLocalization() {
        return new FlashcardLocalization(Locale.of("EN"), new FlashcardContent("Question?", "Answer"));
    }

    public static final FlashcardLocalization polishLocalization() {
        return new FlashcardLocalization(Locale.of("PL"), new FlashcardContent("Pytanie?", "Odpowiedz"));
    }

    public static final FlashcardLocalization frenchLocalization() {
        return new FlashcardLocalization(Locale.of("FR"), new FlashcardContent("Question?", "Reponse"));
    }

    public static final FlashcardLocalization germanLocalization() {
        return new FlashcardLocalization(Locale.of("DE"), new FlashcardContent("Frage?", "Antwort"));
    }

}
