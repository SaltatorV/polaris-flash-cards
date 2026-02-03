package com.saltatorv.polaris.flash.cards.domain;

import java.util.List;
import java.util.Locale;

@Generated
class FlashcardMetadata {
    private final String source;
    private final List<String> tags;
    private final Locale language;

    FlashcardMetadata(String source, List<String> tags, Locale language) {
        this.source = source;
        this.tags = tags;
        this.language = language;
    }

    String getSource() {
        return source;
    }

    List<String> getTags() {
        return tags;
    }

    String getLanguage() {
        return language.getLanguage();
    }
}
