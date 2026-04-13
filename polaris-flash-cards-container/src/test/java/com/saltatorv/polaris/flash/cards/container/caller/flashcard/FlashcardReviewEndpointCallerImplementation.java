package com.saltatorv.polaris.flash.cards.container.caller.flashcard;

import com.saltatorv.polaris.flash.cards.application.query.review.dto.FlashcardReviewDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewLifecycleController.FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static com.saltatorv.polaris.flash.cards.web.controller.query.review.FlashcardReviewViewController.FLASHCARD_REVIEW_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardReviewEndpointCallerImplementation implements FlashcardReviewEndpointCaller,
        LifecycleFlashcardReviewEndpointCaller, ViewFlashcardReviewEndpointCaller {
    String flashcardReviewId;

    public static FlashcardReviewEndpointCaller build() {
        return new FlashcardReviewEndpointCallerImplementation();
    }

    // FlashcardReviewEndpointCaller
    @Override
    public LifecycleFlashcardReviewEndpointCaller generateRandomFlashcardReview(int flashcardCount) {
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

    @Override
    public LifecycleFlashcardReviewEndpointCaller generateFlashcardReview() {
        //to-change
        flashcardReviewId = UUID.randomUUID().toString();
        return this;
    }

    @Override
    public LifecycleFlashcardReviewEndpointCaller generateInvalidFlashcardReview() {
        flashcardReviewId = UUID.randomUUID().toString();
        return this;
    }

    // LifecycleFlashcardReviewEndpointCaller
    @Override
    public LifecycleFlashcardReviewEndpointCaller begin() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_BEGIN_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    @Override
    public LifecycleFlashcardReviewEndpointCaller finish() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_FINISH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    @Override
    public LifecycleFlashcardReviewEndpointCaller drawNext(List<String> drewQuestions) {
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

    @Override
    public LifecycleFlashcardReviewEndpointCaller markAsIncorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_INCORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    @Override
    public LifecycleFlashcardReviewEndpointCaller markAsCorrect() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .post(BASE_API_ENDPOINT + FLASHCARD_REVIEW_MARK_CORRECT_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());
        return this;
    }

    @Override
    public ViewFlashcardReviewEndpointCaller view() {
        return this;
    }

    // LifecycleFlashcardReviewEndpointCaller

    @Override
    public LifecycleFlashcardReviewEndpointCaller lifecycle() {
        return this;
    }

    @Override
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
