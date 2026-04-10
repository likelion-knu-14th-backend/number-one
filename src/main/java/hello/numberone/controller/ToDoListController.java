package hello.numberone.controller;

import hello.numberone.dto.ToDoListRequestDto;
import hello.numberone.dto.ToDoListResponseDto;
import hello.numberone.enity.ToDoList;
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

    // 학생 등록
    @PostMapping
    public ToDoListResponseDto createToDoList(@RequestBody ToDoListRequestDto request) {
        return toDoListService.createToDoList(request);
    }

    // 전체 학생 조회
    @GetMapping
    public List<ToDoListResponseDto> getToDoList() {
        return toDoListService.getToDoList();
    }

    // 학번 기준 단건 조회
    @GetMapping("/{toDoListId}")
    public ToDoListResponseDto getToDoList(@PathVariable Long toDoListId) {
        return toDoListService.getToDoList(toDoListId);
    }

    // 학번 기준 수정
    @PutMapping("/{toDoListId}")
    public ToDoListResponseDto updateToDoList(
            @PathVariable Long toDoListId,
            @RequestBody ToDoListRequestDto request
    ) {
        return toDoListService.updateToDoList(toDoListId, request);
    }

    // 학번 기준 삭제
    @DeleteMapping("/{toDoListId}")
    public void deleteToDoList(@PathVariable Long toDoListId) {
        toDoListService.deleteToDoList(toDoListId);
    }
}
