package hello.numberone.user.service;

import hello.numberone.product.data.dto.ToDoListResponseDto;
import hello.numberone.user.data.dto.request.ProfileRequestDto;
import hello.numberone.user.data.dto.response.ProfileResponseDto;
import hello.numberone.user.data.entity.Profile;
import hello.numberone.user.data.entity.User;
import hello.numberone.user.data.exception.UserNotFoundException;
import hello.numberone.user.data.repository.ProfileRepository;
import hello.numberone.user.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    @Transactional
    public ProfileResponseDto updateProfile(String email, ProfileRequestDto request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        user.getProfile().update(
                request.getBio(),
                request.getPhoneNum()
        );

        return new ProfileResponseDto(user.getProfile());
    }
}
