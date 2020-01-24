package de.qaware.cinema.rest.utils;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.et.MovieET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.ArrayList;
import java.util.List;

public class MovieProvider {

    private static MovieDto preFilledMovieDtoWith(Long id, String title, String country, int launch, String category, int version, int averageVote, List<String> comments) {
        return new MovieDto(
                id,
                title,
                country,
                launch,
                category,
                version,
                averageVote,
                comments

        );
    }

    private static MovieET preFilledMovieETWith(Long id, String title, String country, int launch, String category, int version, List<VoteET> votes, List<CommentET> comments) {
        return new MovieET(
                id,
                title,
                country,
                launch,
                category,
                version,
                votes,
                comments
        );
    }

    private static VoteET preFilledVoteETWith(Long id, MovieET movieET, int vote) {
        return new VoteET(
                id,
                movieET,
                vote
        );
    }
    private static CommentET preFilledCommentETWith(Long id, MovieET movieET, String comment) {
        return new CommentET(
                id,
                movieET,
                comment
        );
    }

    private static Long reusableIdWith(Long id) {
        return id;
    }

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieDtoWith(1111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 4, 3, new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieDtoWith(2222222222L, "La vita è bella", "Italy", 1997, "Drama", 5, 4, new ArrayList<>());
    }

    public static MovieET preFilledMovieET() {
        return preFilledMovieETWith(1111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 4, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieET preFilledMovieET2() {
        return preFilledMovieETWith(2222222222L, "La vita è bella", "Italy", 1997, "Drama", 5, new ArrayList<>(), new ArrayList<>());
    }

    public static VoteET preFilledVoteET() {
        return preFilledVoteETWith(1111111111L, preFilledMovieET(), 3);
    }

    public static VoteET preFilledVoteET2() {
        return preFilledVoteETWith(2222222222L, preFilledMovieET2(), 4);
    }

    public static CommentET preFilledCommentET() {
        return preFilledCommentETWith(1111111111L, preFilledMovieET(), "Great Movie");
    }

    public static CommentET preFilledCommentET2() {
        return preFilledCommentETWith(2222222222L, preFilledMovieET2(), "Awesome Movie");
    }

    public static Long reusableId() {
        return reusableIdWith(1003046238L);
    }

    public static Long reusableId2() {
        return reusableIdWith(1003336238L);
    }

}
