package hello.numberone.user.data.oauth.service;


import hello.numberone.common.security.JwtTokenProvider;
import hello.numberone.user.data.entity.User;
import hello.numberone.user.data.enums.Role;
import hello.numberone.user.data.oauth.dto.KakaoTokenResponseDto;
import hello.numberone.user.data.oauth.dto.KakaoUserInfoResponseDto;
import hello.numberone.user.data.oauth.dto.SocialLoginResponseDto;
import hello.numberone.user.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoOAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    @Value("${kakao.user-info-uri}")
    private String userInfoUri;

    public SocialLoginResponseDto kakaoLogin(String code) {
        String kakaoAccessToken = getKakaoAccessToken(code);
        KakaoUserInfoResponseDto userInfo = getKakaoUserInfo(kakaoAccessToken);
        return loginOrSignup(userInfo);
    }

    private String getKakaoAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        System.out.println("clientId = " + clientId);
        System.out.println("redirectUri = " + redirectUri);
        System.out.println("code = " + code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<KakaoTokenResponseDto> response = restTemplate.postForEntity(
                tokenUri,
                request,
                KakaoTokenResponseDto.class
        );

        return response.getBody().getAccess_token();
    }

    private KakaoUserInfoResponseDto getKakaoUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserInfoResponseDto> response = restTemplate.exchange(
                userInfoUri,
                HttpMethod.GET,
                request,
                KakaoUserInfoResponseDto.class
        );

        KakaoUserInfoResponseDto body = response.getBody();

        System.out.println("userInfo id = " + (body != null ? body.getId() : null));
        System.out.println("userInfo kakao_account = " + (body != null ? body.getKakao_account() : null));
        System.out.println("userInfo properties = " + (body != null ? body.getProperties() : null));

        return body;
    }

    private SocialLoginResponseDto loginOrSignup(KakaoUserInfoResponseDto userInfo) {
        String provider = "KAKAO";
        String providerId = String.valueOf(userInfo.getId());

        String email = null;
        String nickname = "카카오사용자";

        if (userInfo.getKakao_account() != null) {
            email = userInfo.getKakao_account().getEmail();

            if (userInfo.getKakao_account().getProfile() != null
                    && userInfo.getKakao_account().getProfile().getNickname() != null) {
                nickname = userInfo.getKakao_account().getProfile().getNickname();
            }
        }

        if (userInfo.getProperties() != null
                && userInfo.getProperties().getNickname() != null) {
            nickname = userInfo.getProperties().getNickname();
        }

        String finalEmail = (email != null && !email.isBlank())
                ? email
                : "kakao_" + providerId + "@kakao.local";

        String finalNickname = nickname;

        return userRepository.findByProviderAndProviderId(provider, providerId)
                .map(user -> {
                    String jwt = jwtTokenProvider.generateToken(user);
                    return new SocialLoginResponseDto(user.getName(), jwt, false);
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(finalNickname);
                    newUser.setEmail(finalEmail);
                    newUser.setUserNumber("KAKAO_" + providerId);
                    newUser.setRole(Role.USER);
                    newUser.setProvider(provider);
                    newUser.setProviderId(providerId);
                    newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));

                    User savedUser = userRepository.save(newUser);
                    String jwt = jwtTokenProvider.generateToken(savedUser);

                    return new SocialLoginResponseDto(savedUser.getName(), jwt, true);
                });
    }
}