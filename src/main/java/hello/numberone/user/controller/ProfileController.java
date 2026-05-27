package hello.numberone.user.controller;

import hello.numberone.user.data.dto.request.ProfileRequestDto;
import hello.numberone.user.data.dto.response.ProfileResponseDto;
import hello.numberone.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PutMapping("/{id}")
    public ProfileResponseDto updateProfile(
            @RequestHeader("USER-ID") Long userId,
            @RequestBody ProfileRequestDto request
    ) {
        return profileService.updateProfile(userId, request);
    }
}
