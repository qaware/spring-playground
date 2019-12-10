package de.qaware.cinema.business.dto;

import lombok.Data;

@Data
public class MovieDto {

    private Long id;
    private String title;
    private String country;
    private int launch;
    private String category;
    private int version;

    public MovieDto() {}

    public MovieDto(String title, String country, int launch, String category, int version) {
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
    }

    public MovieDto(Long id, String title, String country, int launch, String category, int version) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
        this.version = version;
    }
}
