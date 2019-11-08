package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.MovieDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    private MovieController sut;

    @MockBean
    private MovieBA movieBAMock;

    private MockMvc mvc;

    //Test constants
    private MovieDto movieDto1 = new MovieDto(1003049238L, "Some Like It Hot", "USA", 1959, "Comedy");
    private MovieDto movieDto2 = new MovieDto(1034049238L, "La vita è bella", "Italy", 1997, "Drama");

    @Before
    public void setUp() {
        sut = new MovieController(movieBAMock);
    }

    @Test
    public void movies() throws Exception {

        //given
        List<MovieDto> mockedMovieDtos = new ArrayList<>();
        mockedMovieDtos.add(movieDto1);
        mockedMovieDtos.add(movieDto2);
        when(movieBAMock.getAllMovies()).thenReturn(mockedMovieDtos);

        //when
        //List<MovieDto> movieDtos = sut.getAllMovies();

        //then
        mvc.perform(get("/movie")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("Some Like It Hot").value(movieDto1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("Italy").value(movieDto2.getCountry()));
    }
}
