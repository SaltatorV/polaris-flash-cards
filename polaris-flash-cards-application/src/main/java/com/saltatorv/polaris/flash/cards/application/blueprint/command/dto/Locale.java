package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Locale {

    PL("pl", "Polish"),
    EN("en", "English"),
    DE("de", "German"),
    FR("fr", "French"),
    ES("es", "Spanish"),
    IT("it", "Italian"),
    PT("pt", "Portuguese"),
    UK("uk", "Ukrainian"),
    CS("cs", "Czech"),
    SK("sk", "Slovak");

    private final String code;
    private final String displayName;

    Locale(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @JsonCreator
    public static Locale fromCode(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }

        return Arrays.stream(values())
                .filter(language -> language.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported language: " + code));
    }

    @JsonValue
    public String code() {
        return code;
    }

    public String displayName() {
        return displayName;
    }
}