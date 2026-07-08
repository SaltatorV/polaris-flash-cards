package com.saltatorv.polaris.flash.cards.container.caller.review.query;

import com.saltatorv.polaris.flash.cards.application.review.query.dto.FlashcardReviewDataDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.review.FlashcardReviewQueryController.FLASHCARD_REVIEW_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardReviewQueryEndpointCaller extends EndpointCaller {

    public static FlashcardReviewQueryEndpointCaller build() {
        return new FlashcardReviewQueryEndpointCaller();
    }

    public FlashcardReviewDataDto getReview(String flashcardReviewId) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .pathParam("reviewId", flashcardReviewId)
                .get(BASE_API_ENDPOINT + FLASHCARD_REVIEW_GET_ENDPOINT)
                .then()
                .extract()
                .response();

        return response.as(FlashcardReviewDataDto.class);
    }
}
