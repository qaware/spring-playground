package de.qaware.cinema.data.et;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class MovieET {

    private @Id @GeneratedValue Long id;
    private String title;
    private String country;
    private int launch;
    private String category;


    public MovieET(String title, String country, int launch, String category) {
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
    }
}
