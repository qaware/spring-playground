package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.exceptions.UpdateFailException;
import de.qaware.cinema.data.actor.ActorRepository;
import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.comment.CommentRepository;
import de.qaware.cinema.data.movie.MovieRepository;
import de.qaware.cinema.data.movie_actor.MovieActorRepository;
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
    private final ActorRepository actorRepository;
    private final MovieActorRepository movieActorRepository;

    @Autowired
    public MovieBAImpl(MovieRepository movieRepository, VoteRepository voteRepository, CommentRepository commentRepository, ActorRepository actorRepository, MovieActorRepository movieActorRepository) {

        this.movieRepository = movieRepository;
        this.voteRepository = voteRepository;
        this.commentRepository = commentRepository;
        this.actorRepository = actorRepository;
        this.movieActorRepository = movieActorRepository;
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
            movieDTOS.add(
                    new MovieDto(movieET.getId(),
                            movieET.getTitle(),
                            movieET.getCountry(),
                            movieET.getLaunch(),
                            movieET.getCategory(),
                            movieET.getVersion(),
                            this.getAverageVote(movieET.getId()),
                            this.getComments(movieET.getId()),
                            this.getActorDTOSForMovie(movieET.getId())
                    ));
        }
        return movieDTOS;
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public MovieDto getMovie(Long id) {
        MovieET movieET = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        return new MovieDto(movieET.getId(), movieET.getTitle(), movieET.getCountry(), movieET.getLaunch(), movieET.getCategory(), movieET.getVersion(), this.getAverageVote(movieET.getId()), this.getComments(movieET.getId()), this.getActorDTOSForMovie(movieET.getId()));
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<ActorET> actorETS = actorRepository.getAllActors();
        List<ActorDto> actorDTOS = new ArrayList<>();

        for (ActorET actorET : actorETS) {
            actorDTOS.add(
                    new ActorDto(actorET.getId(),
                            actorET.getName(),
                            actorET.getAge(),
                            this.getMovieTitlesForActor(actorET.getId())
                    ));
        }
        return actorDTOS;
    }

    @Override
    @Cascade(value = {CascadeType.ALL})
    public ActorDto getActor(Long id) {
        ActorET actorET = actorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        return new ActorDto(actorET.getId(), actorET.getName(), actorET.getAge(), this.getMovieTitlesForActor(actorET.getId()));
    }

    @Override
    public List<String> getMovieTitlesForActor(Long actorId) {
        List<Long> movieIds = movieActorRepository.getAllMovieIdsFromActorId(actorId);
        List<String> movieTitles = new ArrayList<>();
        for (Long movieId : movieIds) {
            movieTitles.add(movieRepository.findTitle(movieId));
        }
        return movieTitles;
    }


    @Override
    public List<ActorET> getActorETSForMovie(Long movieId) {
        List<Long> actorIds = movieActorRepository.getActorIdsFromMovieId(movieId);
        List<ActorET> actorETS = new ArrayList<>();
        for (Long actorId : actorIds) {
            actorETS.add(actorRepository.findById(actorId).orElseThrow(() -> new EntityNotFoundException(Long.toString(actorId))));
        }
        return actorETS;
    }

    @Override
    public List<ActorDto> getActorDTOSForMovie(Long movieId) {
        List<ActorDto> actorDTOS = new ArrayList<>();
        for (ActorET actorET : this.getActorETSForMovie(movieId)) {
            actorDTOS.add(
                    new ActorDto(
                            actorET.getId(),
                            actorET.getName(),
                            actorET.getAge(),
                            this.getMovieTitlesForActor(actorET.getId())
                    )
            );
        }
        return actorDTOS;
    }


    @Override
    @Cascade(value = {CascadeType.ALL})
    public void addNewMovieToDatabase(MovieDto newMovieDto) {
        MovieET newMovieET = new MovieET(newMovieDto.getTitle(), newMovieDto.getCountry(), newMovieDto.getLaunch(), newMovieDto.getCategory(), newMovieDto.getVersion(), new ArrayList<>(), new ArrayList<>());
        movieRepository.save(newMovieET);
    }

    @Override
    @Cascade(value = {CascadeType.DELETE})
    public void deleteMovieById(Long id) {
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

