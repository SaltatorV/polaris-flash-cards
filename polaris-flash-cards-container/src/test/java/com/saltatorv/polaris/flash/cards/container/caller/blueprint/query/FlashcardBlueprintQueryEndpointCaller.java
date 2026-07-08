package com.saltatorv.polaris.flash.cards.container.caller.blueprint.query;

import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintQueryDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.query.dto.FlashcardBlueprintSummaryQueryDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.blueprint.FlashcardBlueprintQueryController.FLASHCARD_BLUEPRINT_GET_BY_CATEGORY_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.blueprint.FlashcardBlueprintQueryController.FLASHCARD_BLUEPRINT_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintQueryEndpointCaller extends EndpointCaller {

    public static FlashcardBlueprintQueryEndpointCaller build() {
        return new FlashcardBlueprintQueryEndpointCaller();
    }

    public FlashcardBlueprintQueryDto getFlashcardBlueprint(String blueprintId) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(configureGetBlueprintEndpoint(blueprintId))
                .then()
                .extract()
                .response();

        return response.as(FlashcardBlueprintQueryDto.class);
    }

    public List<FlashcardBlueprintSummaryQueryDto> getFlashcardBlueprintByCategory(String categoryId) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(configureGetAllBlueprintSummariesForCategory(categoryId))
                .then()
                .extract()
                .response();

        return response.jsonPath()
                .getList(".", FlashcardBlueprintSummaryQueryDto.class);
    }

    private String configureGetBlueprintEndpoint(String blueprintId) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_GET_ENDPOINT.replace("{id}", blueprintId);
    }

    private String configureGetAllBlueprintSummariesForCategory(String categoryId) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_GET_BY_CATEGORY_ENDPOINT.replace("{id}", categoryId);
    }
}
