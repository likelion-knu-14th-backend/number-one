package hello.numberone.dto;

import hello.numberone.entity.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeResponseDto {
    private String subjectName;
    private String grade;

    public GradeResponseDto(Grade grade) {
        this.subjectName = grade.getSubjectName();
        this.grade = grade.getGrade();
    }
}