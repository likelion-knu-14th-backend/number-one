package hello.numberone.service;

import hello.numberone.data.dto.UserRequestDto;
import hello.numberone.data.dto.UserResponseDto;
import hello.numberone.data.entity.Profile;
import hello.numberone.data.entity.User;
import hello.numberone.data.repository.UserRepository;
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
                request.getUsername(),
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

    public UserResponseDto getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(String username, UserRequestDto request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        user.update(
                request.getUsername(),
                request.getEmail()
        );

        if (user.getProfile() != null) {
            user.getProfile().setBio(request.getBio());
            user.getProfile().setPhoneNum(request.getPhoneNum());
        }

        return new UserResponseDto(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        userRepository.delete(user);
    }
}
