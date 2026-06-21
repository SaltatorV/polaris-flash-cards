package com.saltatorv.polaris.flash.cards.container.caller.blueprint;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardBlueprintCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.blueprint.FlashcardBlueprintCreationController.FLASHCARD_BLUEPRINT_CREATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintEndpointCallerImplementation implements EndpointCaller, FlashcardBlueprintEndpointCaller, CreateBlueprintEndpointCaller {

    private List<FlashcardBlueprintCreateDto> dtos;

    private FlashcardBlueprintEndpointCallerImplementation() {
        dtos = new ArrayList<>();
    }

    public static FlashcardBlueprintEndpointCaller build() {
        return new FlashcardBlueprintEndpointCallerImplementation();
    }

    // BlueprintEndpointCaller
    @Override
    public CreateBlueprintEndpointCaller createBlueprint() {
        return this;
    }


    // CreateBlueprintEndpointCaller
    @Override
    public CreateBlueprintEndpointCaller addDefaultBlueprintToRequestBody(int times) {
        for (int i = 0; i < times; i++) {

            List<FlashcardLocalizationCreateDto> localizationCreateDtos =
                    List.of(new FlashcardLocalizationCreateDto(Locale.ENGLISH.toLanguageTag(), String.format("Question-%d", i),
                            String.format("Definition-%d", i)));

            dtos.add(new FlashcardBlueprintCreateDto(
                    "01976e3e-6c52-7000-8c3f-2c4e5d6f7a8b",
                    String.format("Source-%d", i),
                    Set.of(String.format("Tags-%d", i)),
                    localizationCreateDtos
            ));
        }

        return this;
    }

    @Override
    public FlashcardBlueprintEndpointCaller executeCreateAPICall() {
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
