package de.qaware.cinema.data.vote;

import de.qaware.cinema.data.vote.et.VoteET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<VoteET, Long> {

    @Query(value = "SELECT vote FROM vote WHERE movie_id = :movieId ", nativeQuery = true)
    List<Integer> findAllVotesForMovie(Long movieId);

}
