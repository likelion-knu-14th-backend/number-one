package hello.numberone.domain.grade.service;

import hello.numberone.domain.grade.dto.GradeRequestDto;
import hello.numberone.domain.grade.dto.GradeResponseDto;
import hello.numberone.domain.grade.entity.Grade;
import hello.numberone.domain.grade.repository.GradeRepository;
import hello.numberone.domain.student.entity.Student;
import hello.numberone.domain.student.repository.StudentRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

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
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        List<Grade> gradeList = gradeRepository.findAllByStudent(student);

        return gradeList.stream()
                .map(GradeResponseDto::new)
                .toList();
    }

    public GradeResponseDto addStudentGrade(String studentNumber, GradeRequestDto request) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        Grade grade = new Grade();
        grade.setSubjectName(request.getSubjectName());
        grade.setGrade(request.getGrade());
        grade.setStudent(student);

        Grade savedGrade = gradeRepository.save(grade);
        return new GradeResponseDto(savedGrade);
    }

    public void deleteStudentGrade(String studentNumber, Long gradeId) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        Grade grade = gradeRepository.findByIdAndStudent(gradeId, student)
                .orElseThrow(() -> new IllegalArgumentException("해당 성적이 존재하지 않습니다."));

        gradeRepository.delete(grade);
    }
}