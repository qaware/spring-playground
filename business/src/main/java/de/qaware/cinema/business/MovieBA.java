package de.qaware.cinema.business;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.List;

public interface MovieBA {

    int getAverageVote(Long movieId);
    List<String> getComments(Long movieId);
    List<MovieDto> getAllMovies();
    void addNewMovieToDatabase(MovieDto newMovieDto);
    MovieDto getMovie(Long id);
    void deleteMovieById(Long id);
    void updateMovie(Long id, MovieDto updatedMovieDto);
    void addVoteToMovie(Long movieId, VoteET voteET);
    void addCommentToMovie(Long movieId, CommentET commentET);
}
