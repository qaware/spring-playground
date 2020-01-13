package de.qaware.cinema.business.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    private Long id;
    private String title;
    private String country;
    private int launch;
    private String category;
    private int version;
    private int averageVote;
    private List<String> comments;
}
