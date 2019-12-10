package de.qaware.cinema.business.utils;

import de.qaware.cinema.business.dto.MovieDto;
import de.qaware.cinema.data.et.MovieET;

public class MovieProvider {

    private static MovieET preFilledMovieETWith(String title, String country, int launch, String category, int version) {
        return new MovieET(
                title,
                country,
                launch,
                category,
                version
        );
    }

    private static MovieDto preFilledMovieDTOWith(String title, String country, int launch, String category, int version) {
        return new MovieDto(
                title,
                country,
                launch,
                category,
                version

        );
    }

    private static Long reusableIdWith(Long id) {
        return id;
    }

    public static MovieET preFilledMovieET() {
        return preFilledMovieETWith("Some Like It Hot", "USA", 1959, "Comedy", 7);
    }

    public static MovieET preFilledMovieET2() {
        return preFilledMovieETWith("La vita è bella", "Italy", 1997, "Drama", 3);
    }

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieDTOWith("Some Like It Hot", "USA", 1959, "Comedy", 7);
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieDTOWith("La vita è bella", "Italy", 1997, "Drama", 3);
    }

    public static Long reusableId()  {
        return reusableIdWith(1003046238L);
    }
}
