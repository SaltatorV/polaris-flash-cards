package com.saltatorv.polaris.flash.cards.container.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;

@Import(PostgresConfiguration.class)
@Testcontainers
public class BaseE2ETest extends RestAssuredConfiguration {

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
