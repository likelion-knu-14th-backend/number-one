package hello.numberone.data.entity;

import hello.numberone.data.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    private String title;

    private LocalDate dueDate;

    private Boolean isCompleted;

    private Priority priority;

    public ToDoList(String number, String title, LocalDate dueDate, Boolean isCompleted, Priority priority) {
        this.number = number;
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public void update(String number, String title, LocalDate dueDate, Boolean isCompleted, Priority priority) {
        this.number = number;
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public void finish() {
        this.isCompleted = true;
    }
}
