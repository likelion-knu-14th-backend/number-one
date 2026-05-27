package hello.numberone.domain.movie.controller;

import hello.numberone.domain.movie.dto.MovieCreateRequestDto;
import hello.numberone.domain.movie.dto.MovieResponseDto;
import hello.numberone.domain.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Movie CRUD API")
public class MovieController {

    private final MovieService movieService;

    // 영화 등록
    @PostMapping
    @Operation(summary = "영화 등록")
    public MovieResponseDto createMovie(@Valid @RequestBody MovieCreateRequestDto request) {
        return movieService.createMovie(request);
    }

    // 전체 영화 조회
    @GetMapping
    @Operation(summary = "전체 영화 조회")
    public List<MovieResponseDto> getMovies() {
        return movieService.getMovies();
    }

    // ID 기준 영화 조회
    @GetMapping("/{id}")
    @Operation(summary = "ID 기준 영화 조회")
    public MovieResponseDto getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    // 제목 기준 영화 조회
    @GetMapping("/search/title")
    @Operation(summary = "제목 기준 영화 조회")
    public MovieResponseDto getMovieByTitle(@RequestParam String title) {
        return movieService.getMovieByTitle(title);
    }

    // 영화 수정
    @PutMapping("/{id}")
    @Operation(summary = "영화 수정")
    public MovieResponseDto updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieCreateRequestDto request
    ) {
        return movieService.updateMovie(id, request);
    }

    // 영화 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "영화 삭제")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
