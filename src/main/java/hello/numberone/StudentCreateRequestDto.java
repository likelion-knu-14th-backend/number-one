package hello.numberone;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentCreateRequestDto {

    private String name;
    private String studentNumber;
    private Integer age;
    private String major;
}
