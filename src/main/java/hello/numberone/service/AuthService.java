package hello.numberone.service;

import hello.numberone.dto.LoginRequestDto;
import hello.numberone.dto.SignupRequestDto;
import hello.numberone.dto.TokenResponseDto;
import hello.numberone.entity.Role;
import hello.numberone.entity.Student;
import hello.numberone.exception.AlreadyEmailExistsException;
import hello.numberone.exception.InvalidPasswordException;
import hello.numberone.exception.StudentNotFoundException;
import hello.numberone.repository.StudentRepository;
import hello.numberone.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signup(SignupRequestDto request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyEmailExistsException();
        }

        Student student = new Student();
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setName(request.getName());
        student.setStudentNumber(request.getStudentNumber());
        student.setAge(request.getAge());
        student.setMajor(request.getMajor());
        student.setRole(Role.STUDENT);

        studentRepository.save(student);
    }

    public TokenResponseDto login(LoginRequestDto request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(StudentNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = jwtTokenProvider.generateToken(student);

        return new TokenResponseDto(student.getName(), accessToken);
    }
}