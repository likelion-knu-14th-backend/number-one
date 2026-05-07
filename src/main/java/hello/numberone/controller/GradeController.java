package hello.numberone.controller;

import hello.numberone.dto.GradeRequestDto;
import hello.numberone.dto.GradeResponseDto;
import hello.numberone.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @PostMapping("/{studentNumber}")
    public void uploadStudentGrade(
            @PathVariable("studentNumber") String studentNumber,
            @Valid @RequestBody List<GradeRequestDto> gradeRequestDtoList
    ) {
        gradeService.uploadStudentGrade(studentNumber, gradeRequestDtoList);
    }

    @GetMapping("/{studentNumber}")
    public List<GradeResponseDto> getStudentGrade(
            @PathVariable("studentNumber") String studentNumber) {
        return gradeService.getStudentGrade(studentNumber);
    }
}