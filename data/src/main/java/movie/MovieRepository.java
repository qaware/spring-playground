package movie;

import movie.et.MovieET;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository {

    @Query
    List<MovieET>getAllMovies();
}
