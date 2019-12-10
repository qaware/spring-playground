package de.qaware.cinema.data;

import de.qaware.cinema.data.et.MovieET;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieET, Long> {

    @Query(value = "SELECT * FROM movies ", nativeQuery = true)
    List<MovieET>getAllMovies();

//    @Cascade(value = {CascadeType.ALL})
//    @Query(value = "INSERT INTO movies " +
//            "VALUES (:movieET) ", nativeQuery = true)
//    void addNewMovieToDatabase(@Param(value = "movieET") MovieET movieET);

    Optional<MovieET> findById(Long id);

    void deleteById(Long id);

    @Cascade(value = {CascadeType.ALL})
    @Query(value = "UPDATE movies " +
            "SET title = :updatedTitle, " +
            "country = :updatedCountry, " +
            "launch = :updatedLaunch, " +
            "category = :updatedCategory " +
            "WHERE id = :updatedId ", nativeQuery = true)
    void updateMovie(@Param(value = "updatedId") Long updatedId,
                     @Param(value = "updatedTitle") String updatedTitle,
                     @Param(value = "updatedCountry") String updatedCountry,
                     @Param(value = "updatedLaunch") int updatedLaunch,
                     @Param(value = "updatedCategory") String updatedCategory
                     );
}
