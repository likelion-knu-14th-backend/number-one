package hello.numberone.auth.service;

import hello.numberone.auth.dto.LoginRequestDto;
import hello.numberone.auth.dto.SignupRequestDto;
import hello.numberone.auth.enums.Role;
import hello.numberone.auth.exception.AlreadyEmailExistsException;
import hello.numberone.auth.exception.InvalidPasswordException;
import hello.numberone.data.entity.User;
import hello.numberone.data.repository.UserRepository;
import hello.numberone.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    }

    public void login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new InvalidPasswordException();
        }
    }
}
