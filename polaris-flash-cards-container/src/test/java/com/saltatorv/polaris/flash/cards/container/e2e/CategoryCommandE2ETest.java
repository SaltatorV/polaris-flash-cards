package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.controller.command.category.CategoryCreationController.CATEGORY_CREATE_ENDPOINT;
import static com.saltatorv.polaris.flash.cards.web.controller.query.category.CategoryQueryController.CATEGORY_GET_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryCommandE2ETest extends BaseE2ETest {

    @Test
    void testShouldCreateCategory() {
        // given
        var category = new CategoryDto("Programming", null);

        // when
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(category)
                .post(CATEGORY_CREATE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        // then
        var listOfCategories = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(CATEGORY_GET_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().jsonPath()
                .getList(".", com.saltatorv.polaris.flash.cards.application.category.query.dto.CategoryDto.class);

        var programmingCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Programming")).findFirst();
        var javaCategory = listOfCategories.stream().filter(c -> c.getCategoryName().equals("Java")).findFirst();

        assertNotNull(programmingCategory);
        assertNotNull(javaCategory);
    }
}
