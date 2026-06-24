package com.saltatorv.polaris.flash.cards.container.caller.review.command;

import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewLifecycleController.FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static com.saltatorv.polaris.flash.cards.web.controller.query.review.FlashcardReviewQueryController.FLASHCARD_REVIEW_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardReviewCommandEndpointCallerImplementation {
    String flashcardReviewId;

    public static FlashcardReviewCommandEndpointCallerImplementation build() {
        return new FlashcardReviewCommandEndpointCallerImplementation();
    }

    // FlashcardReviewEndpointCaller
    public FlashcardReviewCommandEndpointCallerImplementation generateRandomFlashcardReview(int flashcardCount) {
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

    public FlashcardReviewCommandEndpointCallerImplementation query() {
        return this;
    }

    // LifecycleFlashcardReviewEndpointCaller
    public FlashcardReviewCommandEndpointCallerImplementation begin() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_BEGIN_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    public FlashcardReviewCommandEndpointCallerImplementation finish() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_FINISH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    public FlashcardReviewCommandEndpointCallerImplementation drawNext(List<String> drewQuestions) {
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

    public FlashcardReviewCommandEndpointCallerImplementation markAsIncorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    public FlashcardReviewCommandEndpointCallerImplementation markAsCorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    // LifecycleFlashcardReviewEndpointCaller

    public FlashcardReviewCommandEndpointCallerImplementation lifecycle() {
        return this;
    }

    public FlashcardReviewDataDto getReview() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .get(BASE_API_ENDPOINT + FLASHCARD_REVIEW_GET_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(FlashcardReviewDataDto.class);
    }
}
