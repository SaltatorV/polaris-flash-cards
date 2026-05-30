package com.saltatorv.polaris.flash.cards.domain;

import java.util.Locale;

class FlashcardLocalization {
    private final Locale locale;
    private final String question;
    private final String answer;

    public FlashcardLocalization(Locale locale, String question, String answer) {
        this.locale = locale;
        this.question = question;
        this.answer = answer;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
