package hello.numberone.domain.grade.repository;

import hello.numberone.domain.grade.entity.Grade;
import hello.numberone.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findAllByStudent(Student student);
    java.util.Optional<Grade> findByIdAndStudent(Long id, Student student);
}
