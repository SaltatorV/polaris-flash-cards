package com.saltatorv.polaris.flash.cards.container.caller.review.command;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewLifecycleController.FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static io.restassured.RestAssured.given;

public class FlashcardReviewCommandEndpointCaller {
    String flashcardReviewId;

    public static FlashcardReviewCommandEndpointCaller build() {
        return new FlashcardReviewCommandEndpointCaller();
    }

    public FlashcardReviewCommandEndpointCaller generateRandomFlashcardReview(int flashcardCount) {
        flashcardReviewId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(flashcardCount)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT)
                .then()
                .extract()
                .asString();

        return this;
    }

    public FlashcardReviewCommandEndpointCaller begin() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_BEGIN_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewCommandEndpointCaller finish() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_FINISH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewCommandEndpointCaller drawNext(List<String> drewQuestions) {
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

    public FlashcardReviewCommandEndpointCaller markAsIncorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardReviewCommandEndpointCaller markAsCorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public String getCurrentFlashcardReviewId() {
        return flashcardReviewId;
    }
}
