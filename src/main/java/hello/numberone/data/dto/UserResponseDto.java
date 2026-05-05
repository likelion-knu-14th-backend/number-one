package hello.numberone.data.dto;

import hello.numberone.data.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String username;
    private String email;

    private String bio;
    private String phoneNum;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();

        if (user.getProfile() != null) {
            this.bio = user.getProfile().getBio();
            this.phoneNum = user.getProfile().getPhoneNum();
        }
    }

}
