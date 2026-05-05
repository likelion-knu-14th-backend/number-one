package hello.numberone.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String email;

    private String bio;
    private String phoneNum;
}
