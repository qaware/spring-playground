package de.qaware.cinema.data.movie;
import de.qaware.cinema.data.movie.et.MovieET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieET, Long> {

    @Query(value = "SELECT * FROM movie ", nativeQuery = true)
    List<MovieET>getAllMovies();

    @Query(value = "SELECT title FROM movie WHERE id = :id", nativeQuery = true)
    String findTitle(Long id);

    Optional <MovieET> findById(Long id);

    void deleteById(Long id);
}
