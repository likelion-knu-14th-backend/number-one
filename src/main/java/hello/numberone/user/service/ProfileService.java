package hello.numberone.user.service;

import hello.numberone.product.data.dto.ToDoListResponseDto;
import hello.numberone.user.data.dto.request.ProfileRequestDto;
import hello.numberone.user.data.dto.response.ProfileResponseDto;
import hello.numberone.user.data.entity.Profile;
import hello.numberone.user.data.exception.UserNotFoundException;
import hello.numberone.user.data.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public ProfileResponseDto updateProfile(Long userId, ProfileRequestDto request) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        profile.update(
                request.getBio(),
                request.getPhoneNum()
        );

        return new ProfileResponseDto(profile);
    }
}
