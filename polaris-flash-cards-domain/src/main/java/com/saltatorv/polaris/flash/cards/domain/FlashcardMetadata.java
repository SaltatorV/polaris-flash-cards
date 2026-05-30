package com.saltatorv.polaris.flash.cards.domain;

import java.util.Set;

@Generated
public class FlashcardMetadata {
    private final String source;
    private final Set<String> tags;

    public FlashcardMetadata(String source, Set<String> tags) {
        this.source = source;
        this.tags = tags;
    }

    public String getSource() {
        return source;
    }

    public Set<String> getTags() {
        return tags;
    }
}
