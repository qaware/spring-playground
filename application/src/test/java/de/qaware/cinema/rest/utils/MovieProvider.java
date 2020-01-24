package de.qaware.cinema.rest.utils;

import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.et.MovieET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.ArrayList;
import java.util.List;

public class MovieProvider {

    private static MovieDto preFilledMovieDtoWith(Long id, String title, String country, int launch, String category, int version, int averageVote, List<String> comments, List<ActorDto> actors) {
        return new MovieDto(
                id,
                title,
                country,
                launch,
                category,
                version,
                averageVote,
                comments,
                actors

        );
    }

    private static MovieET preFilledMovieETWith(Long id, String title, String country, int launch, String category, int version, List<VoteET> votes, List<CommentET> comments, List<ActorET> actors) {
        return new MovieET(
                id,
                title,
                country,
                launch,
                category,
                version,
                votes,
                comments,
                actors
        );
    }

    public static VoteET preFilledVoteET() {
        return new VoteET(
                2222222222L,
                preFilledMovieET(),
                3
        );
    }
    public static CommentET preFilledCommentET() {
        return new CommentET(
                1111111111L,
                preFilledMovieET2(),
                "Great Movie"
        );
    }

    private static ActorDto preFilledActorDTOWith(Long id, String name, int age, List<String> movies) {
        return new ActorDto(
                id,
                name,
                age,
                movies
        );
    }

    private static Long reusableIdWith(Long id) {
        return id;
    }

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieDtoWith(1111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 4, 3, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieDtoWith(2222222222L, "La vita è bella", "Italy", 1997, "Drama", 5, 4, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieET preFilledMovieET() {
        return preFilledMovieETWith(1111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 4, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public static MovieET preFilledMovieET2() {
        return preFilledMovieETWith(2222222222L, "La vita è bella", "Italy", 1997, "Drama", 5, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public static ActorDto preFilledActorDto() {
        return preFilledActorDTOWith(123456789L, "Jennifer Aniston", 50, new ArrayList<>());
    }

    public static ActorDto preFilledActorDto2() {
        return preFilledActorDTOWith(987654321L, "Diane Kruger", 43, new ArrayList<>());
    }

    public static Long reusableId() {
        return reusableIdWith(1003046238L);
    }

    public static Long reusableId2() {
        return reusableIdWith(1003336238L);
    }

}
