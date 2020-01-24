package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        movieBA.addNewMovieToDatabase(newMovieDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/movie/find/{id}")
    public MovieDto getMovie(@PathVariable Long id) {
        return movieBA.getMovie(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @DeleteMapping("/api/movie/delete/{id}")
    public void deleteMovieById(@PathVariable Long id) {
        movieBA.deleteMovieById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/update/{id}")
    public void updateMovie(@PathVariable Long id, @RequestBody MovieDto updatedMovieDto) {
        movieBA.updateMovie(id, updatedMovieDto);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/vote/{movieId}")
    public void addVoteToMovie(@PathVariable Long movieId, @RequestBody VoteET newVoteET) {
        movieBA.addVoteToMovie(movieId, newVoteET);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/movie/comment/{movieId}")
    public void addCommentToMovie(@PathVariable Long movieId, @RequestBody CommentET newCommentET) {
        movieBA.addCommentToMovie(movieId, newCommentET);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/api/actor")
    public List<ActorDto> getAllActors() {
        return movieBA.getAllActors();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/api/actor/find/{id}")
    public ActorDto getActor(@PathVariable Long id) {
        return movieBA.getActor(id);
    }
}
