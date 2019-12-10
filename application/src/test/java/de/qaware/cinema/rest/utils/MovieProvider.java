package de.qaware.cinema.rest.utils;

import de.qaware.cinema.business.dto.MovieDto;

public class MovieProvider {

    private static MovieDto preFilledMovieWith(Long id, String title, String country, int launch, String category, int version) {
        return new MovieDto(
                id,
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

    public static MovieDto preFilledMovieDto() {
        return preFilledMovieWith(1111111111L, "Some Like It Hot", "USA", 1959, "Comedy", 4);
    }

    public static MovieDto preFilledMovieDto2() {
        return preFilledMovieWith(2222222222L, "La vita è bella", "Italy", 1997, "Drama", 5);
    }

    public static Long reusableId()  {
        return reusableIdWith(1003046238L);
    }

}
