package com.saltatorv.polaris.flash.cards.domain;

enum Answer {
    CORRECT(true), INCORRECT(false), NOT_ANSWERED(false);

    private final boolean isSuccessfulAnswer;

    Answer(boolean isSuccessfulAnswer) {
        this.isSuccessfulAnswer = isSuccessfulAnswer;
    }

    boolean isSuccessfulAnswer() {
        return isSuccessfulAnswer;
    }
}
