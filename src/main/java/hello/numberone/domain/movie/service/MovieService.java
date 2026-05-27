package hello.numberone.domain.movie.service;

import hello.numberone.domain.movie.dto.MovieCreateRequestDto;
import hello.numberone.domain.movie.dto.MovieResponseDto;
import hello.numberone.domain.movie.entity.Movie;
import hello.numberone.domain.movie.repository.MovieRepository;
import hello.numberone.infra.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public List<MovieResponseDto> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public MovieResponseDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        return new MovieResponseDto(movie);
    }

    @Transactional(readOnly = true)
    public MovieResponseDto getMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title)
                .orElseThrow(MovieNotFoundException::new);

        return new MovieResponseDto(movie);
    }

    @Transactional
    public MovieResponseDto updateMovie(Long id, MovieCreateRequestDto request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

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

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        movieRepository.delete(movie);
    }
}
