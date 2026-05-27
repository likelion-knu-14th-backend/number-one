package hello.numberone.product.controller;

import hello.numberone.product.data.dto.ToDoListRequestDto;
import hello.numberone.product.data.dto.ToDoListResponseDto;
import hello.numberone.product.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ToDoListResponseDto createTask(
            @AuthenticationPrincipal String email,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.createTask(email, request);
    }

    @GetMapping
    public List<ToDoListResponseDto> getTasks(
            @AuthenticationPrincipal String email
    ) {
        return toDoListService.getTasks(email);
    }

    @GetMapping("/{id}")
    public ToDoListResponseDto getTask(
            @AuthenticationPrincipal String email,
            @PathVariable Long id
    ) {
        return toDoListService.getTask(email, id);
    }

    @PutMapping("/{id}")
    public ToDoListResponseDto updateTask(
            @AuthenticationPrincipal String email,
            @PathVariable Long id,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.updateTask(email, id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(
            @AuthenticationPrincipal String email,
            @PathVariable Long id
    ) {
        toDoListService.deleteTask(email, id);
    }

    @PatchMapping("/{id}")
    public ToDoListResponseDto finishTask(
            @AuthenticationPrincipal String email,
            @PathVariable Long id
    ) {
        return toDoListService.finishTask(email, id);
    }
}