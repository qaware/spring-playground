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


import javax.persistence.EntityNotFoundException;
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
        return movieDtos;

    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addNewMovieToDatabase(MovieDto newMovieDto) {
        LOGGER.info("Did you get to the BAImpl?");
        LOGGER.info("What happend to the:");
        MovieET newMovieET = new MovieET(newMovieDto.getTitle(), newMovieDto.getCountry(), newMovieDto.getLaunch(), newMovieDto.getCategory());
        LOGGER.info("Print newly created movieETs:");
        movieRepository.save(newMovieET);
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public MovieDto getMovie(Long id) {
        LOGGER.info("Did you get to the BAImpl-getMovie()?");
        MovieET movieET = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        return new MovieDto(movieET.getId(), movieET.getTitle(), movieET.getCountry(), movieET.getLaunch(), movieET.getCategory());
    }

    @Override
    @Cascade(value = {CascadeType.DELETE})
    public void deleteMovieById(Long id) {
        LOGGER.info("Did you get to the BAImpl-Delete?");
        movieRepository.deleteById(id);
    }

    @Override
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    public void updateMovie(MovieDto updatedMovieDto) {
        LOGGER.info("Did you get to the updateMovie in the BAImpl?");
        MovieET updatedMovieET = new MovieET(updatedMovieDto.getId(), updatedMovieDto.getTitle(), updatedMovieDto.getCountry(), updatedMovieDto.getLaunch(), updatedMovieDto.getCategory());
//        Long updatedId = updatedMovieDto.getId();
//        String updatedTitle = updatedMovieDto.getTitle();
//        String updatedCountry = updatedMovieDto.getCountry();
//        int updatedLaunch = updatedMovieDto.getLaunch();
//        String updatedCategory = updatedMovieDto.getCategory();
//        LOGGER.info("Print newly created movieETs for EDIT");
//        movieRepository.updateMovie(updatedId, updatedTitle, updatedCountry, updatedLaunch, updatedCategory);
        movieRepository.save(updatedMovieET);
    }
}

