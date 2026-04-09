package hello.numberone.service;

import hello.numberone.data.dto.ToDoListRequestDto;
import hello.numberone.data.dto.ToDoListResponseDto;
import hello.numberone.data.entity.ToDoList;
import hello.numberone.data.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    public ToDoListResponseDto createTask(ToDoListRequestDto request) {
        ToDoList task = new ToDoList(
                request.getNumber(),
                request.getTitle(),
                request.getDueDate(),
                request.getIsCompleted(),
                request.getPriority()
        );

        ToDoList savedTask = toDoListRepository.save(task);
        return new ToDoListResponseDto(savedTask);
    }

    public List<ToDoListResponseDto> getTasks() {
        return toDoListRepository.findAll()
                .stream()
                .map(ToDoListResponseDto::new)
                .toList();
    }

    public ToDoListResponseDto getTask(String number) {
        ToDoList toDoList = toDoListRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("해당 task가 없습니다"));

        return new ToDoListResponseDto(toDoList);
    }

    public ToDoListResponseDto updateTask(String number, ToDoListRequestDto request) {
        ToDoList toDoList = toDoListRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("해당 task가 없습니다"));

        toDoList.update(
                request.getNumber(),
                request.getTitle(),
                request.getDueDate(),
                request.getIsCompleted(),
                request.getPriority()
        );

        ToDoList updatedTask = toDoListRepository.save(toDoList);
        return new ToDoListResponseDto(updatedTask);
    }

    public void deleteTask(String number) {
        ToDoList toDoList = toDoListRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("해당 task가 없습니다"));

        toDoListRepository.delete(toDoList);
    }

    public ToDoListResponseDto finishTask(String number) {
        ToDoList toDoList = toDoListRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("해당 task가 없습니다"));

        toDoList.finish();

        ToDoList finishedTask = toDoListRepository.save(toDoList);
        return new ToDoListResponseDto(finishedTask);
    }
}
