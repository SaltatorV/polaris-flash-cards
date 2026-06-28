package com.saltatorv.polaris.flash.cards.container.caller.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.category.CategoryQueryController.CATEGORY_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class CategoryQueryEndpointCaller {

    public static CategoryQueryEndpointCaller build() {
        return new CategoryQueryEndpointCaller();
    }

    public List<CategoryDto> getCategory(String parentCategoryId) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .given()
                .queryParam("parentCategoryId", parentCategoryId)
                .when()
                .get(BASE_API_ENDPOINT + CATEGORY_GET_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", CategoryDto.class);
    }
}
