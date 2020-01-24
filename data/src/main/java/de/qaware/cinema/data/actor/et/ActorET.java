package de.qaware.cinema.data.actor.et;

import de.qaware.cinema.data.movie.et.MovieET;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "actor")
@NoArgsConstructor
@AllArgsConstructor
public class ActorET {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Type(type = "text")
    @Column(name = "name")
    private String name;

    @Type(type = "int")
    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "actors")
    private List<MovieET> movies = new ArrayList<>();
}
