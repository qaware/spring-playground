package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/movie")
    public List<MovieDto> getAllMovies() {
        return movieBA.getAllMovies();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/movie/add")
    public void addNewMovieToDatabase(@RequestBody MovieDto newMovieDto) {
        LOGGER.info("addNewMovieToDatabase in Controller reached?");
        LOGGER.info(newMovieDto);
        LOGGER.info("Hi add controller");
        movieBA.addNewMovieToDatabase(newMovieDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/movie/find/{id}")
    public MovieDto getMovie(@PathVariable Long id) {
        LOGGER.info("getMovie in Controller reached?");
        LOGGER.info(id);
        return movieBA.getMovie(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/api/movie/delete/{id}")
    public void deleteMovieById(@PathVariable Long id) {
        LOGGER.info("deleteMovieById in Controller reached?");
        movieBA.deleteMovieById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/update/{id}")
    public void updateMovie(@PathVariable Long id, @RequestBody MovieDto updatedMovieDto) {
        LOGGER.info("updateMovie in Controller reached?");
        LOGGER.info(id);
        LOGGER.info(updatedMovieDto);
        movieBA.updateMovie(id, updatedMovieDto);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/vote/{movieId}")
    public void addVoteToMovie(@PathVariable Long movieId, @RequestBody VoteET newVoteET) {
        LOGGER.info("addVoteToMovie in Controller reached?");
        LOGGER.info(movieId);
        LOGGER.info(newVoteET);
        movieBA.addVoteToMovie(movieId, newVoteET);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/comment/{movieId}")
    public void addCommentToMovie(@PathVariable Long movieId, @RequestBody CommentET newCommentET) {
        LOGGER.info("addCommentToMovie in Controller reached?");
        LOGGER.info(movieId);
        LOGGER.info(newCommentET);
        movieBA.addCommentToMovie(movieId, newCommentET);
    }
}
