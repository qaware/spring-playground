package movie;

import lombok.extern.slf4j.Slf4j;
import movie.dto.MovieDto;
import movie.et.MovieET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MovieBAImpl implements MovieBA{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieBAImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieET> movieETS = movieRepository.getAllMovies();
        List<MovieDto> movieDtos = new ArrayList<MovieDto>();

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
}

