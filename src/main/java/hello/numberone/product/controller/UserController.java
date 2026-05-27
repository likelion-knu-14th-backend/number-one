package hello.numberone.product.controller;

import hello.numberone.product.data.dto.ProfileRequestDto;
import hello.numberone.product.data.dto.UserRequestDto;
import hello.numberone.product.data.dto.UserResponseDto;
import hello.numberone.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PutMapping("/{username}")
    public UserResponseDto updateUser(
            @PathVariable String username,
            @RequestBody ProfileRequestDto request
    ) {
        return userService.updateProfile(username, request);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

}
