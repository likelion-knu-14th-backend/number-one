package hello.numberone.product.data.dto;

import hello.numberone.product.data.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListRequestDto {

    private String number;
    private String title;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private Priority priority;
}