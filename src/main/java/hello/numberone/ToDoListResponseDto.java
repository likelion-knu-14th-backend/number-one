package hello.numberone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ToDoListResponseDto {

    private String number;
    private String title;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private Priority priority;
}
