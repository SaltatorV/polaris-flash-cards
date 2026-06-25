package com.saltatorv.polaris.flash.cards.container.caller.review.command;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static io.restassured.RestAssured.given;

public class FlashcardReviewOperationCommandEndpointCaller {
    String flashcardReviewId;

    public static FlashcardReviewOperationCommandEndpointCaller build() {
        return new FlashcardReviewOperationCommandEndpointCaller();
    }

    public FlashcardReviewOperationCommandEndpointCaller begin() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_BEGIN_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller finish() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_FINISH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller drawNext(List<String> drewQuestions) {
        String question = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_DRAW_NEXT_ENDPOINT)
                .then()
                .extract()
                .asString();

        drewQuestions.add(question);

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller markAsIncorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewOperationCommandEndpointCaller markAsCorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public void setupReview(String currentFlashcardReviewId) {
        this.flashcardReviewId = currentFlashcardReviewId;
    }

    public String getCurrentFlashcardReviewId() {
        return flashcardReviewId;
    }
}
