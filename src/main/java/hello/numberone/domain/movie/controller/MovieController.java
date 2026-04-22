package hello.numberone.domain.movie.controller;

import hello.numberone.domain.movie.dto.MovieCreateRequestDto;
import hello.numberone.domain.movie.dto.MovieResponseDto;
import hello.numberone.domain.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // 영화 등록
    @PostMapping
    public MovieResponseDto createMovie(@RequestBody MovieCreateRequestDto request) {
        return movieService.createMovie(request);
    }

    // 전체 영화 조회
    @GetMapping
    public List<MovieResponseDto> getMovies() {
        return movieService.getMovies();
    }

    // ID 기준 영화 조회
    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    // 제목 기준 영화 조회
    @GetMapping("/search/title")
    public MovieResponseDto getMovieByTitle(@RequestParam String title) {
        return movieService.getMovieByTitle(title);
    }

    // 영화 수정
    @PutMapping("/{id}")
    public MovieResponseDto updateMovie(
            @PathVariable Long id,
            @RequestBody MovieCreateRequestDto request
    ) {
        return movieService.updateMovie(id, request);
    }

    // 영화 삭제
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

