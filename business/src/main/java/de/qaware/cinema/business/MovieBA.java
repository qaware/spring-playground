package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.List;

public interface MovieBA {

    int getAverageVote(Long movieId);

    List<String> getComments(Long movieId);

    List<MovieDto> getAllMovies();

    MovieDto getMovie(Long id);

    List<ActorDto> getAllActors();

    ActorDto getActor(Long id);

    List<String> getMovieTitlesForActor(Long actorId);

    List<ActorET> getActorETSForMovie(Long movieId);

    List<ActorDto> getActorDTOSForMovie(Long movieId);

    void addNewMovieToDatabase(MovieDto newMovieDto);

    void deleteMovieById(Long id);

    void updateMovie(Long id, MovieDto updatedMovieDto);

    void addVoteToMovie(Long movieId, VoteET voteET);

    void addCommentToMovie(Long movieId, CommentET commentET);
}
