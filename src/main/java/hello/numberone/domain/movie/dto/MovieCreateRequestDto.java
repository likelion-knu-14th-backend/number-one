package hello.numberone.domain.movie.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
public class MovieCreateRequestDto {

    @NotBlank(message = "영화 제목은 필수입니다.")
    private String title;

    @NotBlank(message = "감독명은 필수입니다.")
    private String director;

    @NotBlank(message = "장르는 필수입니다.")
    private String genre;

    @NotBlank(message = "개봉일은 필수입니다.")
    private String releaseDate;

    @NotNull(message = "평점은 필수입니다.")
    @PositiveOrZero(message = "평점은 0 이상이어야 합니다.")
    private Double rating;

    @NotNull(message = "러닝타임은 필수입니다.")
    @PositiveOrZero(message = "러닝타임은 0 이상이어야 합니다.")
    private Integer runtime;
}
