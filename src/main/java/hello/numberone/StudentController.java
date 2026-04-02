package hello.numberone;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<StudentResponseDto> studentStore = new ArrayList<>();

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentCreateRequestDto request) {
        StudentResponseDto student = new StudentResponseDto(
                request.getName(),
                request.getStudentNumber(),
                request.getAge(),
                request.getMajor()
        );

        studentStore.add(student);
        return student;
    }

    @GetMapping
    public List<StudentResponseDto> getStudents() {
        return studentStore;
    }

    @GetMapping("/{studentNumber}")
    public StudentResponseDto getStudent(@PathVariable String studentNumber) {
        for (StudentResponseDto student : studentStore) {
            if (student.getStudentNumber().equals(studentNumber)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{studentNumber}")
    public StudentResponseDto updateStudent(
            @PathVariable String studentNumber,
            @RequestBody StudentCreateRequestDto request
    ) {
        for (int i = 0; i < studentStore.size(); i++) {
            StudentResponseDto student = studentStore.get(i);

            if (student.getStudentNumber().equals(studentNumber)) {
                StudentResponseDto updatedStudent = new StudentResponseDto(
                        request.getName(),
                        request.getStudentNumber(),
                        request.getAge(),
                        request.getMajor()
                );

                studentStore.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null;
    }

    @DeleteMapping("/{studentNumber}")
    public void deleteStudent(@PathVariable String studentNumber) {
        for (int i = 0; i < studentStore.size(); i++) {
            StudentResponseDto student = studentStore.get(i);

            if (student.getStudentNumber().equals(studentNumber)) {
                studentStore.remove(i);
            }
        }
    }
}