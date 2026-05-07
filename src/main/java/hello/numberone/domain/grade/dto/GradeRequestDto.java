package hello.numberone.domain.grade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeRequestDto {

    @NotBlank(message = "과목명은 필수입니다.")
    private String subjectName;

    @NotBlank(message = "학점은 필수입니다.")
    private String grade;
}
