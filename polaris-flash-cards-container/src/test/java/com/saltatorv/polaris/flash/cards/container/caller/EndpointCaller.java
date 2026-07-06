package com.saltatorv.polaris.flash.cards.container.caller;

import io.restassured.response.Response;

public class EndpointCaller {
    public Response response;

    public Response getLastResponse() {
        return response;
    }
}
