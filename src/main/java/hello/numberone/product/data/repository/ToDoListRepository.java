package hello.numberone.product.data.repository;

import hello.numberone.product.data.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    Optional<ToDoList> findByIdAndUserId(Long id, Long userId);

    List<ToDoList> findAllByUserId(Long userId);
}
