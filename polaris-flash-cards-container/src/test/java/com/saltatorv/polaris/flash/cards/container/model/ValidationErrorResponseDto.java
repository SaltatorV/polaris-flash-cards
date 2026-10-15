package com.saltatorv.polaris.flash.cards.container.model;

import java.util.List;

public class ValidationErrorResponseDto extends ErrorResponseDto {
    private List<ValidationErrorDto> errors;

    ValidationErrorResponseDto() {
    }

    public ValidationErrorResponseDto(String type, int status, String code, String message, List<ValidationErrorDto> errors) {
        super(type, status, code, message);
        this.errors = errors;
    }

    public List<ValidationErrorDto> getErrors() {
        return List.copyOf(errors);
    }
}
