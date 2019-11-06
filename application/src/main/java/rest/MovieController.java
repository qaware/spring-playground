package rest;

import lombok.extern.slf4j.Slf4j;
import movie.MovieBA;
import movie.dto.MovieDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RequestMapping(value = "/movies")
@RestController
@Slf4j
public class MovieController {

    private MovieBA movieBA;

    public MovieController(MovieBA movieBA) {

        this.movieBA = movieBA;
        LOGGER.info(movieBA);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity getAllMovies() {
        List<MovieDto> movieDtos = movieBA.getAllMovies();
        LOGGER.info(movieDtos);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieDtos);
    }
}
