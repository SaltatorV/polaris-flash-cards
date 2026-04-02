package com.saltatorv.polaris.flash.cards.container.configuration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

class RestAssuredConfiguration extends SpringBootTestConfiguration {
    private final static String BASE_URI = "http://localhost";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }
}
