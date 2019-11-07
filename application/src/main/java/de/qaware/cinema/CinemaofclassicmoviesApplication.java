package de.qaware.cinema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"de.qaware.cinema", "de.qaware.cinema.rest"})
public class CinemaofclassicmoviesApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaofclassicmoviesApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting CinemaApp ...");
        SpringApplication.run(CinemaofclassicmoviesApplication.class, args);
    }
}
