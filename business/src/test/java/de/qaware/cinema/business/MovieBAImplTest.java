package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.business.utils.MovieProvider;
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
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class MovieBAImplTest {

    private MovieBAImpl sut;

    @Mock
    private MovieRepository movieRepositoryMock;

    //Test constants
    private MovieET movieET1 = MovieProvider.preFilledMovieET();
    private MovieET movieET2 = MovieProvider.preFilledMovieET2();

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
    public void getAllMovies() {
        //given
        List<MovieET> mockedMovieEts = new ArrayList<>();
        mockedMovieEts.add(movieET1);
        mockedMovieEts.add(movieET2);
        when(movieRepositoryMock.getAllMovies()).thenReturn(mockedMovieEts);

        //when
        List<MovieDto> movieDtos = sut.getAllMovies();

        //then
        assertThat(movieDtos.get(0).getTitle()).isEqualTo(movieET1.getTitle());
        assertThat(movieDtos.get(1).getCategory()).isEqualTo(movieET2.getCategory());
    }

    @Test
    public void addNewMovieToDatabase() {
        //given
        MovieDto newMovieDto = MovieProvider.preFilledMovieDto();

        //when
        sut.addNewMovieToDatabase(newMovieDto);

        //then
        verify(movieRepositoryMock, times(1)).save(MovieProvider.preFilledMovieET());
    }

    @Test
    public void getMovie() {
        //given
        Long id = MovieProvider.reusableId();
        MovieET singleMovieET = MovieProvider.preFilledMovieET();
        when(movieRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(singleMovieET));

        //when
        MovieDto singleMovieDto = sut.getMovie(id);

        //then
        assertThat(singleMovieDto.getTitle()).isEqualTo(singleMovieET.getTitle());
        assertThat(singleMovieDto.getCategory()).isEqualTo(singleMovieET.getCategory());
    }

    @Test
    public void deleteMovieById() {
        //given
        Long id = MovieProvider.reusableId();

        //when
        sut.deleteMovieById(id);

        //then
        verify(movieRepositoryMock, times(1)).deleteById(id);

    }

    @Test
    public void updateMovie() {
        //given
        MovieDto updatedMovieDto = MovieProvider.preFilledMovieDto2();

        //when
        sut.updateMovie(updatedMovieDto);

        //then
        verify(movieRepositoryMock, times(1 )).save(MovieProvider.preFilledMovieET2());
    }

}
