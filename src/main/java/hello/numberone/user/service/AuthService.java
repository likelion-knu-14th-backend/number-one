package hello.numberone.user.service;

import hello.numberone.common.security.JwtTokenProvider;
import hello.numberone.user.data.dto.request.LoginRequestDto;
import hello.numberone.user.data.dto.request.SignupRequestDto;
import hello.numberone.user.data.dto.response.TokenResponseDto;
import hello.numberone.user.data.entity.Profile;
import hello.numberone.user.data.enums.Role;
import hello.numberone.user.data.exception.AlreadyEmailExistsException;
import hello.numberone.user.data.exception.InvalidPasswordException;
import hello.numberone.user.data.entity.User;
import hello.numberone.user.data.repository.ProfileRepository;
import hello.numberone.user.data.repository.UserRepository;
import hello.numberone.user.data.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signup(SignupRequestDto request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyEmailExistsException();
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setName(request.getName());

        user.setRole(Role.USER);

        userRepository.save(user);

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setBio(request.getBio());
        profile.setPhoneNum(request.getPhoneNum());

        profileRepository.save(profile);
    }

    public TokenResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new InvalidPasswordException();
        }

        String accessToken = jwtTokenProvider.generateToken(user);

        return new TokenResponseDto(
                user.getName(),
                accessToken
        );
    }
}
