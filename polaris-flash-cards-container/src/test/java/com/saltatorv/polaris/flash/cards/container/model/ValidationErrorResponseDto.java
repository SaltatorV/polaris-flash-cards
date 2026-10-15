package com.saltatorv.polaris.flash.cards.container.model;

import java.util.List;

public record ValidationErrorResponseDto(
        String type,
        int status,
        String code,
        String message,
        List<ValidationErrorDto> errors) {
}
