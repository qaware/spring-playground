package de.qaware.cinema.business.utils;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.et.MovieET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.ArrayList;
import java.util.List;

public class MovieProvider {


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

    private static MovieDto preFilledMovieDTOWith(Long id, String title, String country, int launch, String category, int version, int averageVote, List<String> comments) {
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

    public static VoteET preFilledVoteET() {
        return new VoteET(
                null,
                null,
                3
        );
    }

    public static CommentET preFilledCommentET() {
        return new CommentET(
                null,
                null,
                "Awesome Movie"
        );
    }

    private static Long reusableIdWith(Long id) {
        return id;
    }


    public static MovieET preFilledMovieET() {
        return preFilledMovieETWith(11111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 7, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieET preFilledMovieET2() {
        return preFilledMovieETWith(22222222222L, "La vita è bella", "Italy", 1997, "Drama", 3, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieDTOWith(11111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 7, 3, new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieDTOWith(22222222222L, "La vita è bella", "Italy", 1997, "Drama", 3, 4, new ArrayList<>());
    }

//    public static MovieDto optimisticLockDto() {
//        return preFilledMovieDTOWith(123456789L, "OptimisticLock", "Test", 1234, "Hi", 4, 3, new ArrayList<>());
//    }


    public static Long reusableId() {
        return reusableIdWith(11111111111L);
    }

    public static Long reusableId2() { return reusableIdWith(22222222222L); }

}
