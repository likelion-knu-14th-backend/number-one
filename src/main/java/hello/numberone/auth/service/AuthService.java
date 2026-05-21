package hello.numberone.auth.service;

import hello.numberone.auth.common.JwtTokenProvider;
import hello.numberone.auth.dto.LoginRequestDto;
import hello.numberone.auth.dto.SignupRequestDto;
import hello.numberone.auth.dto.TokenResponseDto;
import hello.numberone.auth.enums.Role;
import hello.numberone.auth.exception.AlreadyEmailExistsException;
import hello.numberone.auth.exception.InvalidPasswordException;
import hello.numberone.entity.Student;
import hello.numberone.exception.StudentNotFoundException;
import hello.numberone.repository.StudentRepository;
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
        // 이메일 중복 검사
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyEmailExistsException();
        }

        Student student = new Student();
        // 이메일 저장
        student.setEmail(request.getEmail());

        // 비밀번호 암호화 후 저장
        student.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // 학생 정보 저장
        student.setName(request.getName());
        student.setStudentNumber(request.getStudentNumber());
        student.setAge(request.getAge());
        student.setMajor(request.getMajor());

        // 기본 권한 설정
        student.setRole(Role.STUDENT);

        // DB 저장
        studentRepository.save(student);
    }

    public TokenResponseDto login(LoginRequestDto request) {
        // 이메일로 학생 조회
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(StudentNotFoundException::new);
        // 입력한 비밀번호와 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(
                request.getPassword(),
                student.getPassword()
        )) {
            throw new InvalidPasswordException();
        }
        // 로그인 성공 시 JWT 생성
        String accessToken = jwtTokenProvider.generateToken(student);
        // 사용자 이름과 JWT 반환
        return new TokenResponseDto(
                student.getName(),
                accessToken
        );
    }
}
