package hello.numberone.data.entity;

import hello.numberone.data.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String title;

    private LocalDate dueDate;

    private Boolean isCompleted;

    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
