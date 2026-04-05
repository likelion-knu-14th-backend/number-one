package hello.numberone;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    private final List<ToDoListResponseDto> toDoList = new ArrayList<>();

    @PostMapping
    public ToDoListResponseDto createTask(@RequestBody ToDoListRequestDto request) {
        ToDoListResponseDto task = new ToDoListResponseDto(
                request.getNumber(),
                request.getTitle(),
                request.getDueDate(),
                request.getIsCompleted(),
                request.getPriority()
        );

        toDoList.add(task);
        return task;
    }

    @GetMapping
    public List<ToDoListResponseDto> getTasks() {
        return toDoList;
    }

    @GetMapping("/{number}")
    public ToDoListResponseDto getTask(@PathVariable String number) {
        for (ToDoListResponseDto task : toDoList) {
            if (task.getNumber().equals(number)) {
                return task;
            }
        }
        return null;
    }

    @PutMapping("/{number}")
    public ToDoListResponseDto updateTask(
            @PathVariable String number,
            @RequestBody ToDoListRequestDto request
    ) {
        for (int i = 0; i < toDoList.size(); i++) {
            ToDoListResponseDto task = toDoList.get(i);

            if (task.getNumber().equals(number)) {
                ToDoListResponseDto updatedTask = new ToDoListResponseDto(
                        request.getNumber(),
                        request.getTitle(),
                        request.getDueDate(),
                        request.getIsCompleted(),
                        request.getPriority()
                );

                toDoList.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    @DeleteMapping("/{number}")
    public void deleteTask(@PathVariable String number) {
        for (int i = 0; i < toDoList.size(); i++) {
            ToDoListResponseDto task = toDoList.get(i);

            if (task.getNumber().equals(number)) {
                toDoList.remove(i);
            }
        }
    }

    @PatchMapping("/{number}")
    public ToDoListResponseDto finishTask(@PathVariable String number) {
        for (int i = 0; i < toDoList.size(); i++) {
            ToDoListResponseDto task = toDoList.get(i);

            if (task.getNumber().equals(number)) {
                ToDoListResponseDto finishedTask = new ToDoListResponseDto(
                        task.getNumber(),
                        task.getTitle(),
                        task.getDueDate(),
                        true,
                        task.getPriority()
                );

                toDoList.set(i, finishedTask);
                return finishedTask;
            }
        }
        return null;
    }
}