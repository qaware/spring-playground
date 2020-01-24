package de.qaware.cinema.data.movie_actor;

import de.qaware.cinema.data.actor.et.ActorET;
import de.qaware.cinema.data.movie.et.MovieET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieET, ActorET> {

    @Query(value = "SELECT actor_id FROM movie_actor WHERE movie_id = :movieId", nativeQuery = true)
    List<Long> getActorIdsFromMovieId(Long movieId);

    @Query(value = "SELECT movie_id FROM movie_actor WHERE actor_id = :actorId", nativeQuery = true)
    List<Long> getAllMovieIdsFromActorId(Long actorId);
}
