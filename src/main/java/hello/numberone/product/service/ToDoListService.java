package hello.numberone.product.service;

import hello.numberone.product.data.dto.ToDoListRequestDto;
import hello.numberone.product.data.dto.ToDoListResponseDto;
import hello.numberone.product.data.entity.ToDoList;
import hello.numberone.user.data.entity.User;
import hello.numberone.product.data.repository.ToDoListRepository;
import hello.numberone.user.data.repository.UserRepository;
import hello.numberone.product.exception.TaskNotFoundException;
import hello.numberone.user.data.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final UserRepository userRepository;
    private final ToDoListRepository toDoListRepository;

    @Transactional
    public ToDoListResponseDto createTask(Long userId, ToDoListRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        ToDoList task = new ToDoList(
                request.getNumber(),
                request.getTitle(),
                request.getDueDate(),
                request.getIsCompleted(),
                request.getPriority()
        );

        task.setUser(user);
        toDoListRepository.save(task);
        return new ToDoListResponseDto(task);
    }

    public List<ToDoListResponseDto> getTasks(Long userId) {
        return toDoListRepository.findAllByUserId(userId)
                .stream()
                .map(ToDoListResponseDto::new)
                .toList();
    }

    public ToDoListResponseDto getTask(Long userId, Long id) {
        ToDoList toDoList = findUserTask(userId, id);
        return new ToDoListResponseDto(toDoList);
    }

    @Transactional
    public ToDoListResponseDto updateTask(Long userId, Long id, ToDoListRequestDto request) {
        ToDoList toDoList = findUserTask(userId, id);

        toDoList.update(
                request.getNumber(),
                request.getTitle(),
                request.getDueDate(),
                request.getIsCompleted(),
                request.getPriority()
        );

        return new ToDoListResponseDto(toDoList);
    }

    @Transactional
    public void deleteTask(Long userId, Long id) {
        ToDoList toDoList = findUserTask(userId, id);
        toDoListRepository.delete(toDoList);
    }

    @Transactional
    public ToDoListResponseDto finishTask(Long userId, Long id) {
        ToDoList toDoList = findUserTask(userId, id);
        toDoList.finish();
        return new ToDoListResponseDto(toDoList);
    }


    private ToDoList findUserTask(Long userId, Long id) {
        return toDoListRepository.findByIdAndUserId(id, userId)
                .orElseThrow(TaskNotFoundException::new);
    }
}
