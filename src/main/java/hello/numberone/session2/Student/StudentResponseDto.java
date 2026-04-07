package hello.numberone.session2.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponseDto {
    private String name;
    private String student_id;
    private Integer age;
    private String major;
}
