package de.qaware.cinema.data.actor;

import de.qaware.cinema.data.actor.et.ActorET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<ActorET, Long> {

    @Query(value = "SELECT * FROM actor", nativeQuery = true)
    List<ActorET> getAllActors();

    Optional<ActorET> findById(Long id);
}
