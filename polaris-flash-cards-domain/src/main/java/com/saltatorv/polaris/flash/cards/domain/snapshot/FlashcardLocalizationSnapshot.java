package com.saltatorv.polaris.flash.cards.domain.snapshot;

public class FlashcardLocalizationSnapshot {
    private final String languageCode;
    private final String question;
    private final String answer;

    public FlashcardLocalizationSnapshot(String languageCode, String question, String answer) {
        this.languageCode = languageCode;
        this.question = question;
        this.answer = answer;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
