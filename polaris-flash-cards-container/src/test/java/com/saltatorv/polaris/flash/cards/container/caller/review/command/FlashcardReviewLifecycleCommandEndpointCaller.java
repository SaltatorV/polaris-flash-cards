package com.saltatorv.polaris.flash.cards.container.caller.review.command;

import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewLifecycleController.FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardReviewLifecycleCommandEndpointCaller {

    public static FlashcardReviewLifecycleCommandEndpointCaller build() {
        return new FlashcardReviewLifecycleCommandEndpointCaller();
    }

    public String generateRandomFlashcardReview(int flashcardCount) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(flashcardCount)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT)
                .then()
                .extract()
                .asString();
    }
}
