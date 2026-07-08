package com.saltatorv.polaris.flash.cards.container.caller.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintLocalizationDeleteDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintDeletionController.FLASHCARD_BLUEPRINT_DELETE_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintDeletionController.FLASHCARD_BLUEPRINT_LOCALIZATIONS_DELETE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintDeletionEndpointCaller extends EndpointCaller {

    public static FlashcardBlueprintDeletionEndpointCaller build() {
        return new FlashcardBlueprintDeletionEndpointCaller();
    }

    public FlashcardBlueprintDeletionEndpointCaller deleteFlashcardBlueprint(String blueprintId) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(configureDeleteEndpointForBlueprint(blueprintId))
                .then()
                .extract()
                .response();

        return this;
    }

    public FlashcardBlueprintDeletionEndpointCaller deleteFlashcardLocalizations(String blueprintId, String... locales) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDtosForLocales(locales))
                .when()
                .delete(configureDeleteEndpointForBlueprintLocalization(blueprintId))
                .then()
                .extract()
                .response();

        return this;
    }

    private String configureDeleteEndpointForBlueprint(String blueprintId) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_DELETE_ENDPOINT.replace("{id}", blueprintId);
    }

    private String configureDeleteEndpointForBlueprintLocalization(String blueprintId) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_LOCALIZATIONS_DELETE_ENDPOINT.replace("{id}", blueprintId);
    }

    private List<FlashcardBlueprintLocalizationDeleteDto> createDtosForLocales(String... locales) {
        return Arrays.stream(locales)
                .map(locale -> new FlashcardBlueprintLocalizationDeleteDto(Locale.fromCode(locale)))
                .toList();
    }

}
