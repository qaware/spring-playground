package de.qaware.cinema.data.movie.et;

import de.qaware.cinema.data.comment.et.CommentET;
import de.qaware.cinema.data.vote.et.VoteET;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor

public class MovieET {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Type(type = "text")
    @Column(name = "title")
    private String title;

    @Type(type = "text")
    @Column(name = "country")
    private String country;

    @Type(type = "int")
    @Column(name = "launch")
    private int launch;

    @Type(type = "text")
    @Column(name = "category")
    private String category;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movieET")
    private List<VoteET> votes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movieET")
    private List<CommentET> comments;

    public MovieET(String title, String country, int launch, String category, int version, List<VoteET> votes, List<CommentET> comments) {
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
        this.votes = votes;
        this.comments = comments;
    }
}
