package hello.numberone.user.data.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileRequestDto {

    private String bio;
    private String phoneNum;
}