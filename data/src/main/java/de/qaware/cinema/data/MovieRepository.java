package de.qaware.cinema.data;

import de.qaware.cinema.data.et.MovieET;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieET, Long> {

    @Query(value = "SELECT * FROM movies ", nativeQuery = true)
    List<MovieET>getAllMovies();

//    @Query(value = "INSERT INTO movies " +
//            "VALUES (:movieTitle, :movieCountry, :movieLaunch, :movieCategory) ", nativeQuery = true)
//    MovieET addNewMovieToDatabase(
//                    @Param(value = "movieTitle") String movieTitle,
//                    @Param(value = "movieCountry") String movieCountry,
//                    @Param(value = "movieLaunch") int movieLaunch,
//                    @Param(value = "movieCategory") String movieCategory);

    @Cascade(value = {CascadeType.ALL})
    @Query(value = "INSERT INTO movies " +
            "VALUES (:movieET) ", nativeQuery = true)
    void addNewMovieToDatabase(@Param(value = "movieET") MovieET movieET);

//    @Query(value = "SELECT id FROM movies " +
//            "WHERE id = :movieETForEdit ", nativeQuery = true)
//    MovieET getMovieForEditing(@Param(value = "movieId") Long movieId);
//
//    @Modifying
//    @Cascade(value = {CascadeType.DELETE})
//    @Query(value = "DELETE FROM movies WHERE id = :id ", nativeQuery = true)
//    void deleteMovieById(@Param(value = "id") Long id);


    void deleteById(Long id);
}
