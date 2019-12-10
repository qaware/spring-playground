package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.business.exceptions.UpdateFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping
@Slf4j
public class MovieController {

    private final MovieBA movieBA;

    @Autowired
    public MovieController(MovieBA movieBA) {
        this.movieBA = movieBA;
    }


    @GetMapping("/api/movie")
    public ResponseEntity getAllMovies() {
        List<MovieDto> movieDtos = movieBA.getAllMovies();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieDtos);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/movie/add")
    public ResponseEntity addNewMovieToDatabase(@RequestBody MovieDto newMovieDto) {
        LOGGER.info("addNewMovieToDatabase in Controller reached?");
        LOGGER.info(newMovieDto);
        LOGGER.info("Hi add controller");
        movieBA.addNewMovieToDatabase(newMovieDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/api/movie/find/{id}")
    public ResponseEntity getMovie(@PathVariable String id) {
        LOGGER.info("getMovie in Controller reached?");
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
        LOGGER.info("deleteMovieById in Controller reached?");
        LOGGER.info(id);
        long movieId = Long.parseLong(id);
        LOGGER.info(movieId);
        movieBA.deleteMovieById(movieId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/api/movie/update")
    public void updateMovie(@RequestBody MovieDto updatedMovieDto) throws UpdateFailException {
        LOGGER.info("updateMovie in Controller reached?");
        movieBA.updateMovie(updatedMovieDto);
        LOGGER.info("Hi update controller");
    }
}
