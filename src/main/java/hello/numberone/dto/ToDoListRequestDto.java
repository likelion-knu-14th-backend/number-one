package hello.numberone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ToDoListRequestDto {
    private String title; //할 일 제목
    private Boolean isCompleted; //완료 여부
    private LocalDate dueDate; //마감일
}
