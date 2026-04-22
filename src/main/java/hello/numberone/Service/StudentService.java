package hello.numberone.service;

import hello.numberone.dto.StudentCreateRequestDto;
import hello.numberone.dto.StudentResponseDto;
import hello.numberone.entity.Student;
import hello.numberone.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponseDto createStudent(StudentCreateRequestDto request) {
        Student student = new Student(
                request.getName(),
                request.getStudentNumber(),
                request.getAge(),
                request.getMajor()
        );

        Student savedStudent = studentRepository.save(student);
        return new StudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDto::new)
                .toList();
    }

    public StudentResponseDto getStudent(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        return new StudentResponseDto(student);
    }

    public StudentResponseDto updateStudent(String studentNumber, StudentCreateRequestDto request) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        student.update(
                request.getName(),
                request.getStudentNumber(),
                request.getAge(),
                request.getMajor()
        );

        Student updatedStudent = studentRepository.save(student);
        return new StudentResponseDto(updatedStudent);
    }

    public void deleteStudent(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        studentRepository.delete(student);
    }
}