package hello.numberone.domain.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String director;

    private String genre;

    private String releaseDate;

    private Double rating;

    private Integer runtime;

    public Movie(String title, String director, String genre, String releaseDate, Double rating, Integer runtime) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.runtime = runtime;
    }

    public void update(String title, String director, String genre, String releaseDate, Double rating, Integer runtime) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.runtime = runtime;
    }
}

