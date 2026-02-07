package com.saltatorv.polaris.flash.cards.application.query.dto;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Locale;

public class FlashcardBlueprintDataDto {
    private final FlashcardBlueprintId reviewId;
    private final String question;
    private final String answer;
    private final String source;
    private final List<String> tags;
    private final Locale language;

    public FlashcardBlueprintDataDto(FlashcardBlueprintId reviewId, String question,
                                     String answer, String source,
                                     List<String> tags, Locale language) {
        this.reviewId = reviewId;
        this.question = question;
        this.answer = answer;
        this.source = source;
        this.tags = tags;
        this.language = language;
    }

    public FlashcardBlueprintId getReviewId() {
        return reviewId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSource() {
        return source;
    }

    public List<String> getTags() {
        return tags;
    }

    public Locale getLanguage() {
        return language;
    }
}
