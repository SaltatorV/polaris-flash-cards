package com.saltatorv.polaris.flash.cards.container.caller.blueprint.command;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintMetadataUpdateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationUpdateDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintUpdateController.FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintUpdateController.FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintUpdateEndpointCaller extends EndpointCaller {

    public static FlashcardBlueprintUpdateEndpointCaller build() {
        return new FlashcardBlueprintUpdateEndpointCaller();
    }


    public FlashcardBlueprintUpdateEndpointCaller updateFlashcardBlueprintMetadata(String blueprintId,
                                                                                   FlashcardBlueprintMetadataUpdateDto dto) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .patch(configureUpdateMetadataEndpoint(blueprintId))
                .then()
                .extract()
                .response();

        return this;
    }

    public FlashcardBlueprintUpdateEndpointCaller updateFlashcardLocalizations(String blueprintId, String locale,
                                                                               FlashcardLocalizationUpdateDto dto) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .patch(configureUpdateLocalizationEndpoint(blueprintId, locale))
                .then()
                .extract()
                .response();


        return this;
    }

    private String configureUpdateMetadataEndpoint(String blueprintId) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_UPDATE_METADATA_ENDPOINT.replace("{id}", blueprintId);
    }

    private String configureUpdateLocalizationEndpoint(String blueprintId, String locale) {
        return BASE_API_ENDPOINT + FLASHCARD_BLUEPRINT_UPDATE_LOCALIZATION_ENDPOINT.replace("{id}", blueprintId)
                .replace("{locale}", locale);
    }

}
