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

import javax.persistence.Convert;
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

//    @RequestMapping(value = "/api/movie/add", method = RequestMethod.POST)
//    public ResponseEntity addNewMovieToDatabase(@RequestBody String movieTitle, String movieCountry, Integer movieLaunch, String movieCategory) {
//        LOGGER.info("Add Movie?????????????");
//        LOGGER.info(movieTitle);
//        LOGGER.info(movieCountry);
//        LOGGER.info(movieLaunch);
//        LOGGER.info(movieCategory);
//        LOGGER.info("Hi add controller");
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @RequestMapping(value = "/api/movie/add", method = RequestMethod.POST)
    public ResponseEntity addNewMovieToDatabase(@RequestBody MovieDto movieDto) {
        LOGGER.info("Add Movie?????????????");
        LOGGER.info(movieDto);
        LOGGER.info("Hi add controller");
        movieBA.addNewMovieToDatabase(movieDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/api/movie/{id}")
    public ResponseEntity deleteMovieById(@PathVariable String id) {
        LOGGER.info("Delete Movie?????????????");
        LOGGER.info(id);
        long movieId = Long.parseLong(id);
        LOGGER.info(movieId);
        movieBA.deleteMovieById(movieId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


//    @RequestMapping(value = "/api/movie", method = RequestMethod.GET)
//    public ResponseEntity getMovieForEditing(
//            @RequestParam("movieId") Long movieId) {
//        MovieDto movieDtoForEdit = movieBA.getMovieForEditing(movieId);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(movieDtoForEdit);
//
//
//    }
//



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest() {
        return "Test!";
    }
}
