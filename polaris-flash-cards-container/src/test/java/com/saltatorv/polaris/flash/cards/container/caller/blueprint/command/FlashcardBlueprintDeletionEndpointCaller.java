package com.saltatorv.polaris.flash.cards.container.caller.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintDeletionController.FLASHCARD_BLUEPRINT_DELETE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintDeletionEndpointCaller {

    public static FlashcardBlueprintDeletionEndpointCaller build() {
        return new FlashcardBlueprintDeletionEndpointCaller();
    }

    public FlashcardBlueprintDeletionEndpointCaller deleteFlashcardBlueprint(String blueprintId) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(configureDeleteEndpointForBlueprint(blueprintId))
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    public FlashcardBlueprintDeletionEndpointCaller deleteFlashcardLocalizations(String blueprintId, String... locales) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDtosForLocales(locales))
                .when()
                .delete(configureDeleteEndpointForBlueprint(blueprintId))
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }

    private String configureDeleteEndpointForBlueprint(String blueprintId) {
        return FLASHCARD_BLUEPRINT_DELETE_ENDPOINT.replace("{id}", blueprintId);
    }

    private List<FlashcardBlueprintLocalizationDeleteDto> createDtosForLocales(String... locales) {
        return Arrays.stream(locales).map(FlashcardBlueprintLocalizationDeleteDto::new).toList();
    }

}
