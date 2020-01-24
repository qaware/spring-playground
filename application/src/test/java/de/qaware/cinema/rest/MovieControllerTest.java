package de.qaware.cinema.rest;

import de.qaware.cinema.business.MovieBA;
import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @MockBean
    private MovieBA movieBAMock;

    @Autowired
    private MockMvc mvc;

    //Test constants
    private MovieDto movieDto1 = MovieProvider.preFilledMovieDto();
    private MovieDto movieDto2 = MovieProvider.preFilledMovieDto2();
    private ActorDto actorDto1 = MovieProvider.preFilledActorDto();
    private ActorDto actorDto2 = MovieProvider.preFilledActorDto2();

    @Before
    public void setUp() {
    }

    @Test
    public void getAllMovies() throws Exception {
        //given
        List<MovieDto> mockedMovieDTOS = new ArrayList<>();
        mockedMovieDTOS.add(movieDto1);
        mockedMovieDTOS.add(movieDto2);

        //when
        when(movieBAMock.getAllMovies()).thenReturn(mockedMovieDTOS);


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
        Long id = MovieProvider.reusableId2();
        MovieDto updatedMovieDto = MovieProvider.preFilledMovieDto();

        //when
        movieBAMock.updateMovie(id, updatedMovieDto);

        //then
        mvc.perform(post("/api/movie/update/" + id));

    }

    @Test
    public void addVoteToMovie() throws Exception {
        //given
        Long movieId = MovieProvider.reusableId();
        VoteET newVoteET = MovieProvider.preFilledVoteET();

        //when
        movieBAMock.addVoteToMovie(movieId, newVoteET);

        //then
        mvc.perform(post("/api/movie/vote/" + movieId));
    }

    @Test
    public void addCommentToMovie() throws Exception {
        //given
        Long movieId = MovieProvider.reusableId2();
        CommentET newCommentET = MovieProvider.preFilledCommentET();

        //when
        movieBAMock.addCommentToMovie(movieId, newCommentET);

        //then
        mvc.perform(post("/api/movie/comment/" + movieId));
    }

    @Test
    public void getAllActors() throws Exception {
        //given
        List<ActorDto> mockedActorDTOS = new ArrayList<>();
        mockedActorDTOS.add(actorDto1);
        mockedActorDTOS.add(actorDto2);

        //when
        when(movieBAMock.getAllActors()).thenReturn(mockedActorDTOS);

        //then
        mvc.perform(get("/api/actor")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(actorDto1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(actorDto2.getId()));
    }

    @Test
    public void getActor() throws Exception {
        //given
        Long id = MovieProvider.reusableId2();
        ActorDto getActorDto = MovieProvider.preFilledActorDto();
        when(movieBAMock.getActor(id)).thenReturn(getActorDto);

        //when
        when(movieBAMock.getActor(id)).thenReturn(getActorDto);

        //then
        mvc.perform(get("/api/actor/find/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(getActorDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(getActorDto.getName()));
    }
}
