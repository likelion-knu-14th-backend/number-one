package hello.numberone.product.controller;

import hello.numberone.product.data.dto.ToDoListRequestDto;
import hello.numberone.product.data.dto.ToDoListResponseDto;
import hello.numberone.product.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ToDoListResponseDto createTask(
            @RequestHeader("USER-ID") Long userId,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.createTask(userId, request);
    }

    @GetMapping
    public List<ToDoListResponseDto> getTasks(
            @RequestHeader("USER-ID") Long userId
    ) {
        return toDoListService.getTasks(userId);
    }

    @GetMapping("/{id}")
    public ToDoListResponseDto getTask(
            @RequestHeader("USER-ID") Long userId,
            @PathVariable Long id
    ) {
        return toDoListService.getTask(userId, id);
    }

    @PutMapping("/{id}")
    public ToDoListResponseDto updateTask(
            @RequestHeader("USER-ID") Long userId,
            @PathVariable Long id,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.updateTask(userId, id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(
            @RequestHeader("USER-ID") Long userId,
            @PathVariable Long id
    ) {
        toDoListService.deleteTask(userId, id);
    }

    @PatchMapping("/{id}")
    public ToDoListResponseDto finishTask(
            @RequestHeader("USER-ID") Long userId,
            @PathVariable Long id
    ) {
        return toDoListService.finishTask(userId, id);
    }
}