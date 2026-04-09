package hello.numberone.controller;

import hello.numberone.data.dto.ToDoListRequestDto;
import hello.numberone.data.dto.ToDoListResponseDto;
import hello.numberone.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ToDoListResponseDto createTask(@RequestBody ToDoListRequestDto request) {
        return toDoListService.createTask(request);
    }

    @GetMapping
    public List<ToDoListResponseDto> getTasks() {
        return toDoListService.getTasks();
    }

    @GetMapping("/{number}")
    public ToDoListResponseDto getTask(@PathVariable String number) {
        return toDoListService.getTask(number);
    }

    @PutMapping("/{number}")
    public ToDoListResponseDto updateTask(
            @PathVariable String number,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.updateTask(number, request);
    }

    @DeleteMapping("/{number}")
    public void deleteTask(@PathVariable String number) {
        toDoListService.deleteTask(number);
    }

    @PatchMapping("/{number}")
    public ToDoListResponseDto finishTask(@PathVariable String number) {
        return toDoListService.finishTask(number);
    }
}