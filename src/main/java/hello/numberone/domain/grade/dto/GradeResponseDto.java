package hello.numberone.domain.grade.dto;

import hello.numberone.domain.grade.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeResponseDto {
    private String subjectName;
    private String grade;
    private Long id;

    public GradeResponseDto(Grade grade) {
        this.subjectName = grade.getSubjectName();
        this.grade = grade.getGrade();
        this.id = grade.getId();
    }
}
