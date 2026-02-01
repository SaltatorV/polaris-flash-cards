package com.saltatorv.polaris.flash.cards.domain;

import java.util.List;
import java.util.Locale;

@Generated
class FlashcardMetadata {
    private final String source;
    private final List<String> tags;
    private final Locale language;

    public FlashcardMetadata(String source, List<String> tags, Locale language) {
        this.source = source;
        this.tags = tags;
        this.language = language;
    }

    public String getSource() {
        return source;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getLanguage() {
        return language.getLanguage();
    }
}
