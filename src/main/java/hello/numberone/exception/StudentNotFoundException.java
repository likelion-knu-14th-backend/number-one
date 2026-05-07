package hello.numberone.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("해당 학생이 존재하지 않습니다.");
    }
}
