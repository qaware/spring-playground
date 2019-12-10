package de.qaware.cinema.data.et;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.Id;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movies")
@NoArgsConstructor

public class MovieET {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public MovieET(String title, String country, int launch, String category) {
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
    }

    public MovieET(String title, String country, int launch, String category, int version) {
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
    }

    public MovieET(Long id, String title, String country, int launch, String category, int version) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
    }
}
