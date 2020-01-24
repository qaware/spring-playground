package de.qaware.cinema.business.utils;

import de.qaware.cinema.business.dto.ActorDto;
import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.movie.et.MovieET;
import de.qaware.cinema.data.vote.et.VoteET;

import java.util.ArrayList;
import java.util.List;

public class MovieProvider {


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

    private static MovieDto preFilledMovieDTOWith(Long id, String title, String country, int launch, String category, int version, int averageVote, List<String> comments, List<ActorDto> actors) {
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

    public static ActorET preFilledActorETWith(Long id, String name, int age, List<MovieET> movieETS) {
        return new ActorET(
                id,
                name,
                age,
                movieETS
        );
    }

    public static List<String> preFilledListOfMovieTitles() {
        return new ArrayList<>();
    }

    public static List<ActorET> preFilledListOfActorETS() {
        return new ArrayList<>(
        );
    }

    private static Long reusableIdWith(Long id) {
        return id;
    }


    public static MovieET preFilledMovieET() {
        return preFilledMovieETWith(11111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 7, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public static MovieET preFilledMovieET2() {
        return preFilledMovieETWith(22222222222L, "La vita è bella", "Italy", 1997, "Drama", 3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieDTOWith(11111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 7, 3, new ArrayList<>(), new ArrayList<>());
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieDTOWith(22222222222L, "La vita è bella", "Italy", 1997, "Drama", 3, 4, new ArrayList<>(), new ArrayList<>());
    }

    public static ActorET preFilledActorET() {
        return preFilledActorETWith(123456789L, "Jennifer Aniston", 50, new ArrayList<>());
    }

    public static ActorET preFilledActorET2() {
        return preFilledActorETWith(987654321L, "Diane Kruger", 43, new ArrayList<>());
    }

    public static Long reusableId() {
        return reusableIdWith(11111111111L);
    }

    public static Long reusableId2() {
        return reusableIdWith(22222222222L);
    }

    public static int vote = 5;
    public static int vote2 = 1;
}
