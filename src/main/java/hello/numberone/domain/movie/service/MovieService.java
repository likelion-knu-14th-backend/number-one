package hello.numberone.domain.movie.service;

import hello.numberone.domain.movie.dto.MovieCreateRequestDto;
import hello.numberone.domain.movie.dto.MovieResponseDto;
import hello.numberone.domain.movie.entity.Movie;
import hello.numberone.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponseDto createMovie(MovieCreateRequestDto request) {
        Movie movie = new Movie(
                request.getTitle(),
                request.getDirector(),
                request.getGenre(),
                request.getReleaseDate(),
                request.getRating(),
                request.getRuntime()
        );

        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponseDto(savedMovie);
    }

    public List<MovieResponseDto> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieResponseDto::new)
                .toList();
    }

    public MovieResponseDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 존재하지 않습니다."));

        return new MovieResponseDto(movie);
    }

    public MovieResponseDto getMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 존재하지 않습니다."));

        return new MovieResponseDto(movie);
    }

    public MovieResponseDto updateMovie(Long id, MovieCreateRequestDto request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 존재하지 않습니다."));

        movie.update(
                request.getTitle(),
                request.getDirector(),
                request.getGenre(),
                request.getReleaseDate(),
                request.getRating(),
                request.getRuntime()
        );

        Movie updatedMovie = movieRepository.save(movie);
        return new MovieResponseDto(updatedMovie);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 존재하지 않습니다."));

        movieRepository.delete(movie);
    }
}

