package hello.numberone.repository;

import hello.numberone.enity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long>{
    Optional<ToDoList> findByToDoListId(Long id);
}