package com.saltatorv.polaris.flash.cards.container.caller.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintCreationController.FLASHCARD_BLUEPRINT_CREATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintCreationEndpointCaller {

    public static FlashcardBlueprintCreationEndpointCaller build() {
        return new FlashcardBlueprintCreationEndpointCaller();
    }

    public FlashcardBlueprintCreationEndpointCaller executeCreateAPICall(List<FlashcardBlueprintCreateDto> dtos) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dtos)
                .when()
                .post(BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_CREATE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;

    }
}
