package hello.numberone.domain.student.service;

import hello.numberone.domain.grade.entity.Profile;
import hello.numberone.domain.student.dto.StudentCreateRequestDto;
import hello.numberone.domain.student.dto.StudentResponseDto;
import hello.numberone.domain.student.entity.Student;
import hello.numberone.domain.student.repository.StudentRepository;
import hello.numberone.infra.exception.StudentNotFoundException;
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
                .orElseThrow(StudentNotFoundException::new);

        return new StudentResponseDto(student);
    }

    public StudentResponseDto updateStudent(String studentNumber, StudentCreateRequestDto request) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        student.update(
                request.getName(),
                request.getStudentNumber(),
                request.getAge(),
                request.getMajor()
        );

        Profile profile = new Profile();
        profile.setBio(request.getBio());
        profile.setPhoneNum(request.getPhoneNum());
        profile.setStudent(student);

        student.setProfile(profile);

        Student updatedStudent = studentRepository.save(student);
        return new StudentResponseDto(updatedStudent);
    }

    public void deleteStudent(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        studentRepository.delete(student);
    }
}

