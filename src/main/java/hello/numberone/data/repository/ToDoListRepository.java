package hello.numberone.data.repository;

import hello.numberone.data.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    Optional<ToDoList> findByNumber(String number);
}
