package hello.numberone.dto;

import hello.numberone.entity.Student;
import lombok.Getter;

@Getter
public class StudentResponseDto {

    private String name;
    private String studentNumber;
    private Integer age;
    private String major;

    public StudentResponseDto(Student student) {
        this.name = student.getName();
        this.studentNumber = student.getStudentNumber();
        this.age = student.getAge();
        this.major = student.getMajor();
    }
}