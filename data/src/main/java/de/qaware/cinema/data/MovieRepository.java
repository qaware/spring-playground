package de.qaware.cinema.data;

import de.qaware.cinema.data.et.MovieET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieET, Long> {

    List<MovieET>getAllMovies();
}
