package de.qaware.cinema.data.comment.et;

import de.qaware.cinema.data.movie.et.MovieET;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor

public class CommentET {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieET movieET;

    @Type(type = "text")
    @Column(name = "comment")
    private String comment;

    public CommentET(MovieET movieET, String comment) {
        this.movieET = movieET;
        this.comment = comment;
    }
}
