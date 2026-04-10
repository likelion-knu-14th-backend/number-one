package hello.numberone.dto;

import hello.numberone.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListResponseDto {
    private Long id; //순서
    private String title; //할 일 제목
    private Boolean isCompleted; //완료 여부
    private LocalDate dueDate; //마감일

    public ToDoListResponseDto(ToDoList toDoList){
        this.id = toDoList.getId();
        this.title = toDoList.getTitle();
        this.isCompleted = toDoList.getIsCompleted();
        this.dueDate = toDoList.getDueDate();
    }
}
