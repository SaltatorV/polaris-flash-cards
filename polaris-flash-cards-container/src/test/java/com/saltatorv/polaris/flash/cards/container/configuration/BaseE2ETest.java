package com.saltatorv.polaris.flash.cards.container.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(PostgresConfiguration.class)
public class BaseE2ETest extends RestAssuredConfiguration {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabase() {
        jdbcTemplate.execute("DO $$ DECLARE r RECORD; BEGIN " +
                "FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP " +
                "EXECUTE 'TRUNCATE TABLE ' || quote_ident(r.tablename) || ' RESTART IDENTITY CASCADE'; " +
                "END LOOP; END $$;");

        jdbcTemplate.execute("INSERT INTO category_entity (id, category_name, depth) values ('01976e3e-6c52-7000-8c3f-2c4e5d6f7a8b', 'Java', 1)");
    }
}
