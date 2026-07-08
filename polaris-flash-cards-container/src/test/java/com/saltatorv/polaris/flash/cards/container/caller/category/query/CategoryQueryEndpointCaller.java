package com.saltatorv.polaris.flash.cards.container.caller.category.query;

import com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.caller.EndpointCaller;
import org.springframework.http.MediaType;

import java.util.List;

import static com.saltatorv.polaris.flash.cards.web.BaseController.BASE_API_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.category.CategoryQueryController.CATEGORY_GET_ENDPOINT;
import static io.restassured.RestAssured.given;

public class CategoryQueryEndpointCaller extends EndpointCaller {

    public static CategoryQueryEndpointCaller build() {
        return new CategoryQueryEndpointCaller();
    }

    public List<CategoryDto> getCategory(String parentCategoryId) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .given()
                .queryParam("parentCategoryId", parentCategoryId)
                .when()
                .get(BASE_API_ENDPOINT + CATEGORY_GET_ENDPOINT)
                .then()
                .extract()
                .response();

        return response.jsonPath()
                .getList(".", CategoryDto.class);
    }
}
