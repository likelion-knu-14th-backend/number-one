package hello.numberone.domain.grade.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeRequestDto {
    private String subjectName;
    private String grade;
}
