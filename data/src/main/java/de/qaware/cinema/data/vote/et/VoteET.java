package de.qaware.cinema.data.vote.et;

import de.qaware.cinema.data.movie.et.MovieET;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "vote")
@NoArgsConstructor
@AllArgsConstructor

public class VoteET {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieET movieET;

    @Type(type = "int")
    @Column(name = "vote")
    private int vote;

    public VoteET(MovieET movieET, int vote) {
        this.movieET = movieET;
        this.vote = vote;
    }
}
