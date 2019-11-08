package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping
@Slf4j
@EnableAutoConfiguration
public class MovieController {

    private final MovieBA movieBA;

    @Autowired
    public MovieController(MovieBA movieBA) {
        this.movieBA = movieBA;

    }


    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity getAllMovies() {
        LOGGER.error("List of movieDtos ...");
        List<MovieDto> movieDtos = movieBA.getAllMovies();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieDtos);
    }

    @RequestMapping("/test")
    public String getTest() {
        return "Test!";
    }
}
