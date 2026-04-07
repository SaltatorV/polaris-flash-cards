package com.saltatorv.polaris.flash.cards.container.configuration;

import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(PostgresConfiguration.class)
public class BaseE2ETest extends RestAssuredConfiguration {

}
