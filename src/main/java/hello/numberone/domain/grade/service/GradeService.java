package hello.numberone.domain.grade.service;

import hello.numberone.domain.grade.dto.GradeRequestDto;
import hello.numberone.domain.grade.dto.GradeResponseDto;
import hello.numberone.domain.grade.entity.Grade;
import hello.numberone.domain.grade.repository.GradeRepository;
import hello.numberone.domain.student.entity.Student;
import hello.numberone.domain.student.repository.StudentRepository;
import hello.numberone.infra.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public void uploadStudentGrade(
            String studentNumber, List<GradeRequestDto> gradeRequestDtoList) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        List<Grade> gradeList = gradeRequestDtoList.stream()
                .map(dto -> {
                    Grade grade = new Grade();
                    grade.setSubjectName(dto.getSubjectName());
                    grade.setGrade(dto.getGrade());
                    grade.setStudent(student);
                    return grade;
                }).toList();

        gradeRepository.saveAll(gradeList);
    }

    public List<GradeResponseDto> getStudentGrade(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        List<Grade> gradeList = gradeRepository.findAllByStudent(student);

        return gradeList.stream()
                .map(GradeResponseDto::new)
                .toList();
    }

    public GradeResponseDto addStudentGrade(String studentNumber, GradeRequestDto request) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        Grade grade = new Grade();
        grade.setSubjectName(request.getSubjectName());
        grade.setGrade(request.getGrade());
        grade.setStudent(student);

        Grade savedGrade = gradeRepository.save(grade);
        return new GradeResponseDto(savedGrade);
    }

    public void deleteStudentGrade(String studentNumber, Long gradeId) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentNotFoundException::new);

        Grade grade = gradeRepository.findByIdAndStudent(gradeId, student)
                .orElseThrow(StudentNotFoundException::new);

        gradeRepository.delete(grade);
    }
}