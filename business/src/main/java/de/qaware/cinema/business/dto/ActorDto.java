package de.qaware.cinema.business.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDto {

    private Long id;
    private String name;
    private int age;
    private List<String> movies;
}
