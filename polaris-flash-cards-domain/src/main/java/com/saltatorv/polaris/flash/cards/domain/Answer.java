package com.saltatorv.polaris.flash.cards.domain;

public enum Answer {
    CORRECT(true), INCORRECT(false), NOT_ANSWERED(false), REVIEWED(false);

    private final boolean isCorrect;

    Answer(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
