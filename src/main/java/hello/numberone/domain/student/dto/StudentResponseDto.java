package hello.numberone.domain.student.dto;

import hello.numberone.domain.student.entity.Student;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Getter
@Transactional
public class StudentResponseDto {

    private Long id;
    private String name;
    private String studentNumber;
    private Integer age;
    private String major;

    private String bio;
    private String phoneNum;

    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.studentNumber = student.getStudentNumber();
        this.age = student.getAge();
        this.major = student.getMajor();

        this.bio = student.getProfile().getBio();
        this.phoneNum = student.getProfile().getPhoneNum();
    }
}

