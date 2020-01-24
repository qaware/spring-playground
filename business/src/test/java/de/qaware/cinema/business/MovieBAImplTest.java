package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.business.exceptions.UpdateFailException;
import de.qaware.cinema.business.utils.MovieProvider;
import de.qaware.cinema.data.comment.CommentRepository;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.MovieRepository;
import de.qaware.cinema.data.movie.et.MovieET;
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

    //Test constants
    private MovieET movieET1 = MovieProvider.preFilledMovieET();
    private MovieET movieET2 = MovieProvider.preFilledMovieET2();

    @Before
    public void setUp() {
        sut = new MovieBAImpl(movieRepositoryMock, voteRepositoryMock, commentRepositoryMock);

    }

    @Test
    public void getAverageVote() {
        //given
        Long movieId = MovieProvider.reusableId();
        List<Integer> mockedVotes = new ArrayList<>();
        when(voteRepositoryMock.findAllVotesForMovie(movieId)).thenReturn(mockedVotes);

        //when
        int averageVote = sut.getAverageVote(movieId);

        //then
        assertThat(averageVote).isEqualTo(mockedVotes.size());
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
        List<Integer> mockedVotes = new ArrayList<>();
        when(voteRepositoryMock.findAllVotesForMovie(movieET1.getId())).thenReturn(mockedVotes);

        //when
        List<MovieDto> movieDTOS = sut.getAllMovies();

        //then
        assertThat(movieDTOS.get(0).getTitle()).isEqualTo(movieET1.getTitle());
        assertThat(movieDTOS.get(1).getCategory()).isEqualTo(movieET2.getCategory());
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

//    @Test
//    public void updateMovieWithOptimisticLocking() {
//        //given
//        Long id = MovieProvider.reusableId();
//        MovieDto updatedMovieDto = MovieProvider.preFilledMovieDto2();
//        if (updatedMovieDto.getVersion() != )
//        MovieET newMovieET = new MovieET(id,
//                updatedMovieDto.getTitle(),
//                updatedMovieDto.getCountry(),
//                updatedMovieDto.getLaunch(),
//                updatedMovieDto.getCategory(),
//                updatedMovieDto.getVersion(),
//                new ArrayList<>(),
//                new ArrayList<>());
//
//        //when
//        sut.updateMovie(id, updatedMovieDto);
//
//        //then
//        verify(movieRepositoryMock.save(newMovieET));
//
//    }

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
