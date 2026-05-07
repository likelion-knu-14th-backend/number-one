package hello.numberone.domain.student.controller;

import hello.numberone.domain.student.dto.StudentCreateRequestDto;
import hello.numberone.domain.student.dto.StudentResponseDto;
import hello.numberone.domain.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Student CRUD API")
public class StudentController {

    private final StudentService studentService;

    // 학생 등록
    @PostMapping
    @Operation(summary = "학생 등록")
    public StudentResponseDto createStudent(@Valid @RequestBody StudentCreateRequestDto request) {
        return studentService.createStudent(request);
    }

    // 전체 학생 조회
    @GetMapping
    @Operation(summary = "전체 학생 조회")
    public List<StudentResponseDto> getStudents() {
        return studentService.getStudents();
    }

    // 학번 기준 단건 조회
    @GetMapping("/{studentNumber}")
    @Operation(summary = "학번 기준 단건 조회")
    public StudentResponseDto getStudent(@PathVariable String studentNumber) {
        return studentService.getStudent(studentNumber);
    }

    // 학번 기준 수정
    @PutMapping("/{studentNumber}")
    @Operation(summary = "학번 기준 수정")
    public StudentResponseDto updateStudent(
            @PathVariable String studentNumber,
            @Valid @RequestBody StudentCreateRequestDto request
    ) {
        return studentService.updateStudent(studentNumber, request);
    }

    // 학번 기준 삭제
    @DeleteMapping("/{studentNumber}")
    @Operation(summary = "학번 기준 삭제")
    public void deleteStudent(@PathVariable String studentNumber) {
        studentService.deleteStudent(studentNumber);
    }
}

