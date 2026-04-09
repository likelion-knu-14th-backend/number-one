package hello.numberone.data.dto;

import hello.numberone.data.enums.Priority;
import hello.numberone.data.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListResponseDto {

    private String number;
    private String title;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private Priority priority;

    public ToDoListResponseDto(ToDoList toDoList) {
        this.number = toDoList.getNumber();
        this.title = toDoList.getTitle();
        this.dueDate = toDoList.getDueDate();
        this.isCompleted = toDoList.getIsCompleted();
        this.priority = toDoList.getPriority();
    }
}
