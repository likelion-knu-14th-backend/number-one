package hello.numberone.domain.grade.controller;

import hello.numberone.domain.grade.dto.GradeRequestDto;
import hello.numberone.domain.grade.dto.GradeResponseDto;
import hello.numberone.domain.grade.service.GradeService;
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
            @RequestBody List<GradeRequestDto> gradeRequestDtoList
    ) {
        gradeService.uploadStudentGrade(studentNumber, gradeRequestDtoList);
    }

    @GetMapping("/{studentNumber}")
    public List<GradeResponseDto> getStudentGrade(
            @PathVariable("studentNumber") String studentNumber) {
        return gradeService.getStudentGrade(studentNumber);
    }

    @PostMapping("/{studentNumber}/single")
    public GradeResponseDto addStudentGrade(
            @PathVariable("studentNumber") String studentNumber,
            @RequestBody GradeRequestDto gradeRequestDto
    ) {
        return gradeService.addStudentGrade(studentNumber, gradeRequestDto);
    }

    @DeleteMapping("/{studentNumber}/{gradeId}")
    public void deleteStudentGrade(
            @PathVariable("studentNumber") String studentNumber,
            @PathVariable("gradeId") Long gradeId
    ) {
        gradeService.deleteStudentGrade(studentNumber, gradeId);
    }
}