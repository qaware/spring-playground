package movie.dto;

import lombok.Data;

@Data
public class MovieDto {

    private String id;
    private String title;
    private String country;
    private int launch;
    private String category;

    public MovieDto(String id, String title, String country, int launch, String category) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.launch = launch;
        this.category = category;
    }
}
