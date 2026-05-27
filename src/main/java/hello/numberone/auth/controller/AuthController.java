package hello.numberone.auth.controller;

import hello.numberone.auth.dto.LoginRequestDto;
import hello.numberone.auth.dto.SignupRequestDto;
import hello.numberone.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(
            @Valid @RequestBody SignupRequestDto request
    ) {
        authService.signup(request);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        authService.login(request);
        return "로그인 성공";
    }
}
