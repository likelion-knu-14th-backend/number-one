package hello.numberone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true, nullable = false)
    private Boolean isCompleted;

    private LocalDate dueDate;

    public ToDoList(Long id,String title, Boolean isCompleted, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public void update(Long id,String title, Boolean isCompleted, LocalDate dueDate) {
        this.id=id;
        this.title = title;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }
}
