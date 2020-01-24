package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.business.exceptions.UpdateFailException;
import de.qaware.cinema.business.utils.MovieProvider;
import de.qaware.cinema.data.actor.ActorRepository;
import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.comment.CommentRepository;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.MovieRepository;
import de.qaware.cinema.data.movie.et.MovieET;
import de.qaware.cinema.data.movie_actor.MovieActorRepository;
import de.qaware.cinema.data.vote.VoteRepository;
import de.qaware.cinema.data.vote.et.VoteET;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class MovieBAImplTest {

    private MovieBAImpl sut;

    @Mock
    private MovieRepository movieRepositoryMock;
    @Mock
    private VoteRepository voteRepositoryMock;
    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private ActorRepository actorRepositoryMock;
    @Mock
    private MovieActorRepository movieActorRepositoryMock;

    //Test constants
    private MovieET movieET1 = MovieProvider.preFilledMovieET();
    private MovieET movieET2 = MovieProvider.preFilledMovieET2();
    private ActorET actorET = MovieProvider.preFilledActorET();
    private ActorET actorET2 = MovieProvider.preFilledActorET2();


    @Before
    public void setUp() {
        sut = new MovieBAImpl(movieRepositoryMock, voteRepositoryMock, commentRepositoryMock, actorRepositoryMock, movieActorRepositoryMock);

    }

    @Test
    public void getAverageVote() {
        //given
        Long movieId = MovieProvider.reusableId();
        List<Integer> mockedVotes = new ArrayList<>();
        mockedVotes.add(MovieProvider.vote);
        mockedVotes.add(MovieProvider.vote2);
        when(voteRepositoryMock.findAllVotesForMovie(movieId)).thenReturn(mockedVotes);

        //when
        int averageVote = sut.getAverageVote(movieId);

        //then
        assertThat(averageVote).isEqualTo(6 / mockedVotes.size());
    }

    @Test
    public void getComments() {
        //given
        Long movieId = MovieProvider.reusableId();
        List<String> mockedCommentsForMovie = new ArrayList<>();
        when(commentRepositoryMock.getAllCommentsForMovie(movieId)).thenReturn(mockedCommentsForMovie);

        //when
        List<String> resultComments = sut.getComments(movieId);

        //then
        assertThat(resultComments).isEqualTo(mockedCommentsForMovie);
    }

    @Test
    public void getAllMovies() {
        //given
        List<MovieET> mockedMovieEts = new ArrayList<>();
        mockedMovieEts.add(movieET1);
        mockedMovieEts.add(movieET2);
        when(movieRepositoryMock.getAllMovies()).thenReturn(mockedMovieEts);

        //when
        List<MovieDto> movieDTOS = sut.getAllMovies();

        //then
        assertThat(movieDTOS.get(0).getTitle()).isEqualTo(movieET1.getTitle());
        assertThat(movieDTOS.get(0).getCountry()).isEqualTo(movieET1.getCountry());
        assertThat(movieDTOS.get(1).getLaunch()).isEqualTo(movieET2.getLaunch());
        assertThat(movieDTOS.get(1).getCategory()).isEqualTo(movieET2.getCategory());
        assertThat(movieDTOS.get(1).getVersion()).isEqualTo(movieET2.getVersion());
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
    public void getAllActors() {
        //given
        List<ActorET> mockedActorETS = new ArrayList<>();
        mockedActorETS.add(actorET);
        mockedActorETS.add(actorET2);
        when(actorRepositoryMock.getAllActors()).thenReturn(mockedActorETS);

        //when
        List<ActorDto> actorDTOS = sut.getAllActors();

        //then
        assertThat(actorDTOS.get(0).getId()).isEqualTo(actorET.getId());
        assertThat(actorDTOS.get(0).getName()).isEqualTo(actorET.getName());
        assertThat(actorDTOS.get(1).getId()).isEqualTo(actorET2.getId());
        assertThat(actorDTOS.get(1).getName()).isEqualTo(actorET2.getName());
    }

    @Test
    public void getActor() {
        //given
        Long id = MovieProvider.reusableId2();
        ActorET actorET = MovieProvider.preFilledActorET();
        when(actorRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(actorET));

        //when
        ActorDto actorDto = sut.getActor(id);

        //then
        assertThat(actorDto.getId()).isEqualTo(actorET.getId());
        assertThat(actorDto.getName()).isEqualTo(actorET.getName());
    }

    @Test
    public void getMovieTitlesForActor() {
        //given
        Long actorId = MovieProvider.reusableId();
        List<Long> movieIds = new ArrayList<>();
        when(movieActorRepositoryMock.getAllMovieIdsFromActorId(actorId)).thenReturn(movieIds);
        List<String> mockedMovieTitles = MovieProvider.preFilledListOfMovieTitles();

        //when
        List<String> movieTitles = sut.getMovieTitlesForActor(actorId);

        //then
        assertThat(movieTitles).isEqualTo(mockedMovieTitles);

    }

    @Test
    public void getActorETSForMovie() {
        //given
        Long movieId = MovieProvider.reusableId2();
        List<Long> actorIds = new ArrayList<>();
        when(movieActorRepositoryMock.getActorIdsFromMovieId(movieId)).thenReturn(actorIds);
        List<ActorET> actorETS = MovieProvider.preFilledListOfActorETS();

        //when
        List<ActorET> actorETS2 = sut.getActorETSForMovie(movieId);

        //then
        assertThat(actorETS2).isEqualTo(actorETS);
    }

    @Test
    public void getActorDTOSForMovie() {
        //given
        Long movieId = MovieProvider.reusableId();
        List<ActorET> actorETS = MovieProvider.preFilledListOfActorETS();

        //when
        List<ActorDto> actorDTOS = sut.getActorDTOSForMovie(movieId);

        //then
        assertThat(actorDTOS).isEqualTo(actorETS);
    }

    @Test
    public void addNewMovieToDatabase() {
        //given
        MovieDto newMovieDto = MovieProvider.preFilledMovieDto();
        MovieET newMovieET = new MovieET(
                newMovieDto.getTitle(),
                newMovieDto.getCountry(),
                newMovieDto.getLaunch(),
                newMovieDto.getCategory(),
                newMovieDto.getVersion(),
                new ArrayList<>(),
                new ArrayList<>());

        //when
        sut.addNewMovieToDatabase(newMovieDto);

        //then
        verify(movieRepositoryMock, times(1)).save(newMovieET);
    }

    @Test
    public void deleteMovieById() {
        //given
        Long id = MovieProvider.reusableId2();

        //when
        sut.deleteMovieById(id);

        //then
        verify(movieRepositoryMock, times(1)).deleteById(id);
    }

    @Test
    public void updateMovie() {
        //given
        Long id = MovieProvider.reusableId2();
        MovieDto updatedMovieDto = MovieProvider.preFilledMovieDto2();
        MovieET movieET = MovieProvider.preFilledMovieET2();
        when(movieRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(movieET));
        MovieET newMovieET = new MovieET(movieET.getId(),
                updatedMovieDto.getTitle(),
                updatedMovieDto.getCountry(),
                updatedMovieDto.getLaunch(),
                updatedMovieDto.getCategory(),
                updatedMovieDto.getVersion(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        //when
        sut.updateMovie(id, updatedMovieDto);

        //then
        try {
            verify(movieRepositoryMock, times(1)).save(newMovieET);
        } catch (ObjectOptimisticLockingFailureException optimisticLockException) {
            throw new UpdateFailException("Update failed");
        }

    }

    @Test
    public void addVoteToMovie() {
        //given
        Long movieId = MovieProvider.reusableId();
        VoteET voteET = MovieProvider.preFilledVoteET();
        MovieET movieETForVoteET = new MovieET();
        when(movieRepositoryMock.findById(movieId)).thenReturn(java.util.Optional.of(movieETForVoteET));
        VoteET newVoteET = new VoteET(movieETForVoteET, voteET.getVote());

        //when
        sut.addVoteToMovie(movieId, voteET);

        //then
        verify(voteRepositoryMock, times(1)).save(newVoteET);
    }

    @Test
    public void addCommentToMovie() {
        //given
        Long movieId = MovieProvider.reusableId2();
        CommentET commentET = MovieProvider.preFilledCommentET();
        MovieET movieETForCommentET = new MovieET();
        when(movieRepositoryMock.findById(movieId)).thenReturn(java.util.Optional.of(movieETForCommentET));
        CommentET newCommentET = new CommentET(movieETForCommentET, commentET.getComment());

        //when
        sut.addCommentToMovie(movieId, commentET);

        //then
        verify(commentRepositoryMock, times(1)).save(newCommentET);
    }
}
