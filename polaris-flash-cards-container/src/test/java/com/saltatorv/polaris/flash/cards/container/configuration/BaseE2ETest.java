package com.saltatorv.polaris.flash.cards.container.configuration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static io.restassured.RestAssured.given;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseE2ETest {
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer container =
            new PostgreSQLContainer("postgres:16")
                    .withDatabaseName("polaris_flash_cards");

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
