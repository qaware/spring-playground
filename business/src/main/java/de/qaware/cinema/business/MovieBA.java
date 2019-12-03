package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;

import java.util.List;

public interface MovieBA {

    List<MovieDto> getAllMovies();
    void addNewMovieToDatabase(MovieDto newMovieDto);
    MovieDto getMovie(Long id);
    void deleteMovieById(Long id);
    void updateMovie(MovieDto updatedMovieDto);

}
