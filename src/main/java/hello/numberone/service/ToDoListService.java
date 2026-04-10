package hello.numberone.service;

import hello.numberone.dto.ToDoListRequestDto;
import hello.numberone.dto.ToDoListResponseDto;
import hello.numberone.entity.ToDoList;
import hello.numberone.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    public ToDoListResponseDto createToDoList(ToDoListRequestDto request) {
        ToDoList toDoList = new ToDoList(
                request.getTitle(),
                request.getIsCompleted(),
                request.getDueDate()
        );

        ToDoList savedToDoList = toDoListRepository.save(toDoList);
        return new ToDoListResponseDto(savedToDoList);
    }

    public List<ToDoListResponseDto> getToDoList() {
        return toDoListRepository.findAll()
                .stream()
                .map(ToDoListResponseDto::new)
                .toList();
    }

    public ToDoListResponseDto getToDoList(Long ToDoListId) {
        ToDoList toDoList = toDoListRepository.findById(ToDoListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        return new ToDoListResponseDto(toDoList);
    }

    public ToDoListResponseDto updateToDoList(Long toDoListId, ToDoListRequestDto request) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        toDoList.update(
                toDoListId,
                request.getTitle(),
                request.getIsCompleted(),
                request.getDueDate()
        );

        ToDoList updatedToDoList = toDoListRepository.save(toDoList);
        return new ToDoListResponseDto(updatedToDoList);
    }

    public void deleteToDoList(Long toDoListId) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        toDoListRepository.delete(toDoList);
    }
}