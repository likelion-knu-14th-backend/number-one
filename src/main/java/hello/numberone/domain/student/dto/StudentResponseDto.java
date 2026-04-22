package hello.numberone.domain.student.dto;

import hello.numberone.domain.student.entity.Student;
import lombok.Getter;

@Getter
public class StudentResponseDto {

    private Long id;
    private String name;
    private String studentNumber;
    private Integer age;
    private String major;

    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.studentNumber = student.getStudentNumber();
        this.age = student.getAge();
        this.major = student.getMajor();
    }
}

