package hello.numberone.auth.exception;

public class AlreadyEmailExistsException extends RuntimeException {
    public AlreadyEmailExistsException() {
        super("이미 존재하는 이메일입니다.");
    }
}
