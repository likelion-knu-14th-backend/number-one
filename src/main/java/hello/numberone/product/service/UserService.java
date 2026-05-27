package hello.numberone.product.service;

import hello.numberone.product.data.dto.ProfileRequestDto;
import hello.numberone.product.data.dto.UserRequestDto;
import hello.numberone.product.data.dto.UserResponseDto;
import hello.numberone.product.data.entity.Profile;
import hello.numberone.user.data.entity.User;
import hello.numberone.user.data.repository.UserRepository;
import hello.numberone.product.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User(
                request.getName(),
                request.getEmail()
        );

        Profile profile = new Profile();
        profile.setBio(request.getBio());
        profile.setPhoneNum(request.getPhoneNum());
        profile.setUser(user);

        user.setProfile(profile);

        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateProfile(String name, ProfileRequestDto request) {
        User user = userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);

        if (user.getProfile() != null) {
            user.getProfile().update(
                    request.getBio(),
                    request.getPhoneNum()
            );
        }

        return new UserResponseDto(user);
    }

    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }
}
