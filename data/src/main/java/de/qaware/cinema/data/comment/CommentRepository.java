package de.qaware.cinema.data.comment;

import de.qaware.cinema.data.comment.et.CommentET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentET, Long> {

    @Query(value = "SELECT comment FROM comment WHERE movie_id = :movieId ", nativeQuery = true)
    List<String> getAllCommentsForMovie(Long movieId);
}
