package com.saltatorv.polaris.flash.cards.container.e2e;

import com.saltatorv.polaris.flash.cards.application.category.command.dto.CategoryDto;
import com.saltatorv.polaris.flash.cards.container.configuration.BaseE2ETest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.saltatorv.polaris.flash.cards.web.controller.command.category.CategoryCreationController.BASE_LIFECYCLE_ENDPOINT;
import static io.restassured.RestAssured.given;

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
                .post(BASE_LIFECYCLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.OK.value());

        // then
    }
}
