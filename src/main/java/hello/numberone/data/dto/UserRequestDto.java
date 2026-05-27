package hello.numberone.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String name;
    private String email;

    private String bio;
    private String phoneNum;
}
