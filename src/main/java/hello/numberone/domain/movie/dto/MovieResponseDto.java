package hello.numberone.domain.movie.dto;

import hello.numberone.domain.movie.entity.Movie;
import lombok.Getter;

@Getter
public class MovieResponseDto {

    private Long id;
    private String title;
    private String director;
    private String genre;
    private String releaseDate;
    private Double rating;
    private Integer runtime;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.genre = movie.getGenre();
        this.releaseDate = movie.getReleaseDate();
        this.rating = movie.getRating();
        this.runtime = movie.getRuntime();
    }
}

