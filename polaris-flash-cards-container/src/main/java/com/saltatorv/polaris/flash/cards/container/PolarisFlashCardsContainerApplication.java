package com.saltatorv.polaris.flash.cards.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.saltatorv.polaris.flash.cards"})
@EnableJpaRepositories(basePackages = {"com.saltatorv.polaris.flash.cards"})
@SpringBootApplication
public class PolarisFlashCardsContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolarisFlashCardsContainerApplication.class, args);
    }

}
