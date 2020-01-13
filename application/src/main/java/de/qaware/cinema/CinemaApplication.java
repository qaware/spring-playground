package de.qaware.cinema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement
public class CinemaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting CinemaApp ...");
        SpringApplication.run(CinemaApplication.class, args);
    }
}
