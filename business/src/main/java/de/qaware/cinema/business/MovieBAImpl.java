package de.qaware.cinema.business;

import de.qaware.cinema.business.exceptions.UpdateFailException;
import de.qaware.cinema.data.comment.CommentRepository;
import de.qaware.cinema.data.movie.MovieRepository;
import de.qaware.cinema.data.vote.VoteRepository;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;
import lombok.extern.slf4j.Slf4j;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.movie.et.MovieET;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class MovieBAImpl implements MovieBA {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieBAImpl.class);

    private final MovieRepository movieRepository;
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public MovieBAImpl(MovieRepository movieRepository, VoteRepository voteRepository, CommentRepository commentRepository) {

        this.movieRepository = movieRepository;
        this.voteRepository = voteRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public int getAverageVote(Long movieId) {
        List<Integer> votes = voteRepository.findAllVotesForMovie(movieId);
        int averageVote = 0;
        int totalValue = 0;
        for (Integer vote : votes) {
            totalValue += vote;
            averageVote = totalValue / votes.size();
        }
        return averageVote;
    }

    @Override
    public List<String> getComments(Long movieId) {
        return commentRepository.getAllCommentsForMovie(movieId);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieET> movieETS = movieRepository.getAllMovies();
        List<MovieDto> movieDTOS = new ArrayList<>();

        for (MovieET movieET : movieETS) {
            List<Integer> votes = voteRepository.findAllVotesForMovie(movieET.getId());
            int averageVote = 0;
            int totalValue = 0;
            for (Integer vote : votes) {
                totalValue += vote;
                averageVote = totalValue / votes.size();
            }
            movieDTOS.add(
                    new MovieDto(movieET.getId(),
                            movieET.getTitle(),
                            movieET.getCountry(),
                            movieET.getLaunch(),
                            movieET.getCategory(),
                            movieET.getVersion(),
                            averageVote,
                            this.getComments(movieET.getId())
                    ));
        }
        return movieDTOS;

    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addNewMovieToDatabase(MovieDto newMovieDto) {
        MovieET newMovieET = new MovieET(newMovieDto.getTitle(), newMovieDto.getCountry(), newMovieDto.getLaunch(), newMovieDto.getCategory(), newMovieDto.getVersion(), new ArrayList<>(), new ArrayList<>());
        movieRepository.save(newMovieET);
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public MovieDto getMovie(Long id) {
        LOGGER.info("Did you get to the BAImpl-getMovie()?");
        MovieET movieET = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        return new MovieDto(movieET.getId(), movieET.getTitle(), movieET.getCountry(), movieET.getLaunch(), movieET.getCategory(), movieET.getVersion(), this.getAverageVote(movieET.getId()), this.getComments(movieET.getId()));
    }

    @Override
    @Cascade(value = {CascadeType.DELETE})
    public void deleteMovieById(Long id) {
        LOGGER.info("Did you get to the BAImpl-Delete?");
        movieRepository.deleteById(id);
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void updateMovie(Long id, MovieDto updatedMovieDto) {
    MovieET movieET = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        MovieET newMovieET = new MovieET(
                movieET.getId(),
                updatedMovieDto.getTitle(),
                updatedMovieDto.getCountry(),
                updatedMovieDto.getLaunch(),
                updatedMovieDto.getCategory(),
                updatedMovieDto.getVersion(),
                new ArrayList<>(),
                new ArrayList<>());
        try {
            movieRepository.save(newMovieET);
        } catch (ObjectOptimisticLockingFailureException optimisticLockException) {
            LOGGER.info("optimisticLockException catch");
            throw new UpdateFailException("Updating the movie with the id " + updatedMovieDto.getId() + "and the title " + updatedMovieDto.getTitle() + "failed because its values were changed in the meantime");
        }
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addVoteToMovie(Long movieId, VoteET newVoteET) {
        MovieET movieET = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(Long.toString(movieId)));
        VoteET voteET = new VoteET(movieET, newVoteET.getVote());
        voteRepository.save(voteET);
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addCommentToMovie(Long movieId, CommentET newCommentET) {
        MovieET movieET = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(Long.toString(movieId)));
        CommentET commentET = new CommentET(movieET, newCommentET.getComment());
        commentRepository.save(commentET);
    }
}

