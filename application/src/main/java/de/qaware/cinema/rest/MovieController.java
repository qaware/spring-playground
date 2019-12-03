package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/api/movie", method = RequestMethod.GET)
    public ResponseEntity getAllMovies() {
        List<MovieDto> movieDtos = movieBA.getAllMovies();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieDtos);
    }

    @RequestMapping(value = "/api/movie/add", method = RequestMethod.POST)
    public ResponseEntity addNewMovieToDatabase(@RequestBody MovieDto newMovieDto) {
        LOGGER.info("Add Movie?????????????");
        LOGGER.info(newMovieDto);
        LOGGER.info("Hi add controller");
        movieBA.addNewMovieToDatabase(newMovieDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/movie/find/{id}", method = RequestMethod.GET)
    public ResponseEntity getMovie(@PathVariable String id) {
        LOGGER.info("Find Movie By Id in Controller?????????????");
        LOGGER.info(id);
        long movieId = Long.parseLong(id);
        LOGGER.info(movieId);
        MovieDto movieDto = movieBA.getMovie(movieId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieDto);
    }


    @DeleteMapping("/api/movie/delete/{id}")
    public ResponseEntity deleteMovieById(@PathVariable String id) {
        LOGGER.info("Delete Movie?????????????");
        LOGGER.info(id);
        long movieId = Long.parseLong(id);
        LOGGER.info(movieId);
        movieBA.deleteMovieById(movieId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/movie/update", method = RequestMethod.POST)
    public ResponseEntity updateMovie(@RequestBody MovieDto updatedMovieDto) {
        LOGGER.info("Update Movie?????????????");
        movieBA.updateMovie(updatedMovieDto);
        LOGGER.info("Hi update controller");
        return ResponseEntity.ok(HttpStatus.OK);


    }
}
