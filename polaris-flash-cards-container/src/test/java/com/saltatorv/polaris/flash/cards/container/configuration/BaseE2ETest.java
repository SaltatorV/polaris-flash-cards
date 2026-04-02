package com.saltatorv.polaris.flash.cards.container.configuration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;

@Testcontainers
@Import(PostgresConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void testShouldAddFlashcardBlueprint() {
        //given
        given()
                .contentType("application/json")
                .body("""
                        [{
                        "source": "TEST_SOURCE",
                        "tags": ["Java"],
                        "language": "EN",
                        "question": "TEST_QUESTION",
                        "definition": "TEST_DEFINITION"
                        }]""")
                .when()
                .post("/api/v1/flashcard/blueprints")
                .then()
                .statusCode(200);
    }
}
