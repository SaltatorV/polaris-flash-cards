package com.saltatorv.polaris.flash.cards.domain;

enum Answer {
    CORRECT(true), INCORRECT(false), NOT_ANSWERED(false);

    private final boolean isCorrect;

    Answer(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    boolean isCorrect() {
        return isCorrect;
    }
}
