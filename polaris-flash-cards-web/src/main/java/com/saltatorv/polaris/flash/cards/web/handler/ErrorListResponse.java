package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public record ErrorListResponse(String code, List<String> message) {
    public static ErrorListResponse create(String code, List<String> message) {
        return new ErrorListResponse(code, message);
    }
}
