package com.saltatorv.polaris.flash.cards.container.caller.blueprint;

import com.saltatorv.polaris.flash.cards.application.command.blueprint.dto.FlashcardBlueprintDataDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.FlashcardBlueprintController.FLASHCARD_BLUEPRINT_CREATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FlashcardBlueprintEndpointCallerImplementation implements EndpointCaller, FlashcardBlueprintEndpointCaller, CreateBlueprintEndpointCaller {

    private List<FlashcardBlueprintDataDto> dtos;

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
            dtos.add(new FlashcardBlueprintDataDto(
                    String.format("Question-%d", i),
                    String.format("Definition-%d", i),
                    String.format("Source-%d", i),
                    List.of(String.format("Tags-%d", i)),
                    String.format("LG%d", i)
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
