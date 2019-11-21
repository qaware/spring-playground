package de.qaware.cinema.business;

import de.qaware.cinema.data.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.et.MovieET;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MovieBAImpl implements MovieBA {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieBAImpl.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieBAImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieET> movieETS = movieRepository.getAllMovies();
        List<MovieDto> movieDtos = new ArrayList<>();

        for (MovieET movieET : movieETS) {
            movieDtos.add(
                    new MovieDto(movieET.getId(),
                            movieET.getTitle(),
                            movieET.getCountry(),
                            movieET.getLaunch(),
                            movieET.getCategory())
            );
        }
        LOGGER.info("Print list of movieDtos: ", movieDtos);
        return movieDtos;

    }

//    @Override
//    public MovieDto addNewMovieToDatabase(String movieTitle, String movieCountry, int movieLaunch, String movieCategory) {
//        MovieET movieET = movieRepository.addNewMovieToDatabase(movieTitle, movieCountry, movieLaunch, movieCategory);
//        LOGGER.info(movieTitle);
//        return new MovieDto(movieET.getId(),movieET.getTitle(), movieET.getCountry(), movieET.getLaunch(), movieET.getCategory());
//    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addNewMovieToDatabase(MovieDto movieDto) {
        LOGGER.info("Did you get to the BAImpl?");
        LOGGER.info("What happend to the:");
        System.out.println(movieDto);
        MovieET movieET = new MovieET(movieDto.getTitle(), movieDto.getCountry(), movieDto.getLaunch(), movieDto.getCategory());
        LOGGER.info("Print newly created movieETs:");
        System.out.println(movieET);
        movieRepository.save(movieET);
    }

    @Override
    @Cascade(value = {CascadeType.DELETE})
    public void deleteMovieById(Long id) {
        LOGGER.info("Did you get to the BAImpl-Delete?");
        System.out.println(id);
        movieRepository.deleteById(id);
    }

//    @Override
//    public MovieDto getMovieForEditing(Long movieId) {
//        MovieET movieET = movieRepository.getMovieForEditing(movieId);
//        return new MovieDto(movieET.getId(), movieET.getTitle(), movieET.getCountry(), movieET.getLaunch(), movieET.getCategory());
//    }

}

