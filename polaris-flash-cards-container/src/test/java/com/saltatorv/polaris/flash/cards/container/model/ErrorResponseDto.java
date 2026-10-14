package com.saltatorv.polaris.flash.cards.container.model;

public record ErrorResponseDto(
        String type,
        int status,
        String code,
        String message) {
}