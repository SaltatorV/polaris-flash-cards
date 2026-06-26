package com.saltatorv.polaris.flash.cards.container.caller.category.command;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.command.category.CategoryCreationController.CATEGORY_CREATE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class CategoryCreationCommandEndpointCaller {

    public static CategoryCreationCommandEndpointCaller build() {
        return new CategoryCreationCommandEndpointCaller();
    }

    public CategoryCreationCommandEndpointCaller createCategory(CategoryDto dto) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post(BASE_API_ENDPOINT + CATEGORY_CREATE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        return this;
    }
}
