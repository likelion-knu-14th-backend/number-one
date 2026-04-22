package hello.numberone.domain.movie.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieCreateRequestDto {

    private String title;
    private String director;
    private String genre;
    private String releaseDate;
    private Double rating;
    private Integer runtime;
}

