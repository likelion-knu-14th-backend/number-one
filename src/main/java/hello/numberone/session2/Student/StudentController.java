package hello.numberone.session2.Student;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<StudentResponseDto> studentStore = new ArrayList<>();

    // Post
    @PostMapping
    public StudentResponseDto addStudent(@RequestBody StudentCreateRequestDto request) {
        StudentResponseDto student = new StudentResponseDto(
                request.getName(),
                request.getStudent_id(),
                request.getAge(),
                request.getMajor()
        );
        studentStore.add(student);
        return student;
    }

    // GET - 전체 조회
    @GetMapping
    public List<StudentResponseDto> getStudents() {
        return studentStore;
    }

    // GET - 단건 조회
    @GetMapping("/{studentNumber}")
    public StudentResponseDto getStudent(@PathVariable String studentNumber) {
        for (StudentResponseDto student : studentStore) {
            if (student.getStudent_id().equals(studentNumber)) {
                return student;
            }
        }
        return null;
    }
    // PUT - 학번 기준 수정
    @PutMapping("/{studentNumber}")
    public StudentResponseDto updateStudent(
            @PathVariable String studentNumber,
            @RequestBody StudentCreateRequestDto request
    ) {
        for (int i = 0; i < studentStore.size(); i++) {
            StudentResponseDto student = studentStore.get(i);

            if (student.getStudent_id().equals(studentNumber)) {
                StudentResponseDto updatedStudent = new StudentResponseDto(
                        request.getName(),
                        request.getStudent_id(),
                        request.getAge(),
                        request.getMajor()
                );

                studentStore.set(i, updatedStudent);
                return updatedStudent;
            }
        }

        return null;
    }
    // DELETE - 학번 기준 삭제
    @DeleteMapping("/{studentNumber}")
    public void deleteStudent(@PathVariable String studentNumber) {
        studentStore.removeIf(student -> student.getStudent_id().equals(studentNumber));
    }
}
