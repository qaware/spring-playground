package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;

import java.util.List;

public interface MovieBA {

    List<MovieDto> getAllMovies();
    void addNewMovieToDatabase(MovieDto movieDto);
//    MovieDto addNewMovieToDatabase(String movieTitle, String movieCountry, int movieLaunch, String movieCategory);
//    MovieDto getMovieForEditing(Long movieId);
//    MovieDto deleteMovieById(Long movieId);

}
