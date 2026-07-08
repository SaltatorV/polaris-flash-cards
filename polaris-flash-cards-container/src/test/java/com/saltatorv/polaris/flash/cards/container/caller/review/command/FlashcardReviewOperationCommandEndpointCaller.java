package com.saltatorv.polaris.flash.cards.container.caller.review.command;

import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static io.restassured.RestAssured.given;

public class FlashcardReviewOperationCommandEndpointCaller extends EndpointCaller {
    String flashcardReviewId;

    public static FlashcardReviewOperationCommandEndpointCaller build() {
        return new FlashcardReviewOperationCommandEndpointCaller();
    }

    public FlashcardReviewOperationCommandEndpointCaller begin() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_BEGIN_ENDPOINT)
                .then()
                .extract()
                .response();

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller finish() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_FINISH_ENDPOINT)
                .then()
                .extract()
                .response();

        return this;
    }

    public String drawNext() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_DRAW_NEXT_ENDPOINT)
                .then()
                .extract()
                .response();

        return response.asString();
    }

    public FlashcardReviewOperationCommandEndpointCaller markAsIncorrect() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
                .then()
                .extract()
                .response();

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller markAsCorrect() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
                .then()
                .extract()
                .response();

        return this;
    }

    public void setupReview(String currentFlashcardReviewId) {
        this.flashcardReviewId = currentFlashcardReviewId;
    }

    public String getCurrentFlashcardReviewId() {
        return flashcardReviewId;
    }
}
