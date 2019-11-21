package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;

import java.util.List;

public interface MovieBA {

    List<MovieDto> getAllMovies();
    void addNewMovieToDatabase(MovieDto movieDto);
    void deleteMovieById(Long id);
//    MovieDto getMovieForEditing(Long movieId);

}
