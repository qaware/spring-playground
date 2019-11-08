package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.MovieRepository;
import de.qaware.cinema.data.et.MovieET;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class MovieBAImplTest {

    private MovieBAImpl sut;

    @Mock
    private MovieRepository movieRepositoryMock;

    //Test constants
    private MovieET movieET1 = new MovieET("Some Like It Hot", "USA", 1959, "Comedy");
    private MovieET movieET2 = new MovieET("La vita è bella", "Italy", 1997, "Drama");

    @Before
    public void setUp() {
        sut = new MovieBAImpl(movieRepositoryMock);

    }

    private void defaultAsserts(MovieET movieET) {
        assertThat(movieET).isNotNull();
        assertThat(movieET.getId()).isNotNull();
        assertThat(movieET.getTitle()).isNotNull();
        assertThat(movieET.getCountry()).isNotNull();
        assertThat(movieET.getLaunch()).isNotNull();
        assertThat(movieET.getCategory()).isNotNull();

    }

    @Test
    public void createMovieDtoFromMovieET() {
        //given
        List<MovieET> mockedMovieEts = new ArrayList<>();
        mockedMovieEts.add(movieET1);
        mockedMovieEts.add(movieET2);
        when(movieRepositoryMock.getAllMovies()).thenReturn(mockedMovieEts);

        //when
        List<MovieDto> movieDtos = sut.getAllMovies();

        //then
        assertThat(movieDtos.get(0).getTitle().equals(movieET1.getTitle()));
        assertThat(movieDtos.get(1).getCategory().equals(movieET2.getCategory()));
    }
}
