package com.saltatorv.polaris.flash.cards.domain;

import java.util.Locale;

public class FlashcardLocalization {
    private final Locale locale;
    private final FlashcardContent content;

    public FlashcardLocalization(Locale locale, FlashcardContent content) {
        this.locale = locale;
        this.content = content;
    }

    public Locale getLocale() {
        return locale;
    }

    public FlashcardContent getContent() {
        return content;
    }
}
