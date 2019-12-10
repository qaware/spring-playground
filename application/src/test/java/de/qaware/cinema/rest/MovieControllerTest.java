package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.rest.utils.MovieProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    private MovieController sut;

    @MockBean
    private MovieBA movieBAMock;

    @Autowired
    private MockMvc mvc;

    //Test constants
    private MovieDto movieDto1 = MovieProvider.preFilledMovieDto();
    private MovieDto movieDto2 = MovieProvider.preFilledMovieDto2();

    @Before
    public void setUp() {
        sut = new MovieController(movieBAMock);
    }

    @Test
    public void getAllMovies() throws Exception {

        //given
        List<MovieDto> mockedMovieDtos = new ArrayList<>();
        mockedMovieDtos.add(movieDto1);
        mockedMovieDtos.add(movieDto2);

        //when
        when(movieBAMock.getAllMovies()).thenReturn(mockedMovieDtos);


        //then
        mvc.perform(get("/api/movie")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(movieDto1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].country").value(movieDto2.getCountry()));
    }

    @Test
    public void addNewMovieToDatabase() throws Exception {

        //given
        MovieDto newMovieDto = MovieProvider.preFilledMovieDto();

        //when
        movieBAMock.addNewMovieToDatabase(newMovieDto);


        //then
        mvc.perform(post("/api/movie/add"));
    }

    @Test
    public void getMovie() throws Exception {

        //given
        Long id = MovieProvider.reusableId();
        MovieDto getMovieDto = MovieProvider.preFilledMovieDto();
        when(movieBAMock.getMovie(id)).thenReturn(getMovieDto);

        //when
        when(movieBAMock.getMovie(id)).thenReturn(getMovieDto);

        //then
        mvc.perform(get("/api/movie/find/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(getMovieDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(getMovieDto.getCountry()));
    }

    @Test
    public void deleteMovieById() throws Exception {

        //given
        Long id = MovieProvider.reusableId();

        //when
        movieBAMock.deleteMovieById(id);

        //then
        mvc.perform(post("/api/movie/delete/" + id));
    }

    @Test
    public void updateMovie() throws Exception {

        //given
        MovieDto updatedMovieDto = MovieProvider.preFilledMovieDto2();

        //when
        movieBAMock.updateMovie(updatedMovieDto);

        //then
        mvc.perform(post("api/movie/update"));

    }
}
