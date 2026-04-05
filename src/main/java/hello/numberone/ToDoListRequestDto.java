package hello.numberone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ToDoListRequestDto {

    private String number;
    private String title;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private Priority priority;
}