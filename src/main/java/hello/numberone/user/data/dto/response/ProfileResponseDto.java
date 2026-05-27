package hello.numberone.user.data.dto.response;

import hello.numberone.user.data.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {

    private String bio;
    private String phoneNum;

    public ProfileResponseDto(Profile profile) {
        this.bio = profile.getBio();
        this.phoneNum = profile.getPhoneNum();
    }
}
