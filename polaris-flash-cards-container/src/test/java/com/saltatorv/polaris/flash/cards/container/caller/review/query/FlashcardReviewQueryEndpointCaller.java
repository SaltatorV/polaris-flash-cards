package com.saltatorv.polaris.flash.cards.container.caller.review.query;

import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewLifecycleController.FLASHCARD_REVIEW_GENERATE_RANDOM_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.review.FlashcardReviewOperationController.*;
import static com.saltatorv.polaris.flash.cards.web.controller.query.review.FlashcardReviewQueryController.FLASHCARD_REVIEW_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardReviewQueryEndpointCaller {

    public static FlashcardReviewQueryEndpointCaller build() {
        return new FlashcardReviewQueryEndpointCaller();
    }

    public FlashcardReviewDataDto getReview(String flashcardReviewId) {
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
