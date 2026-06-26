package com.saltatorv.polaris.flash.cards.container.caller.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.controller.query.category.CategoryQueryController.BASE_CATEGORY_QUERY_ENDPOINT;
import static io.restassured.RestAssured.given;

public class CategoryQueryEndpointCaller {

    public static CategoryQueryEndpointCaller build() {
        return new CategoryQueryEndpointCaller();
    }

    public List<CategoryDto> getCategory(int depth) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .given()
                .queryParam("depth", depth)
                .when()
                .get(BASE_CATEGORY_QUERY_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", CategoryDto.class);
    }
}
