package hello.numberone.session2.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentCreateRequestDto {
    private String name;
    private String student_id;
    private Integer age;
    private String major;
}
