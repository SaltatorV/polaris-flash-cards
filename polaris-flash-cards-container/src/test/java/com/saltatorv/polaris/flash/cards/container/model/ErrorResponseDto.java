package com.saltatorv.polaris.flash.cards.container.model;

public class ErrorResponseDto {

    private String type;
    private int status;
    private String code;
    private String message;

    public ErrorResponseDto(String type, int status, String code, String message) {
        this.type = type;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}