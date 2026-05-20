package hello.numberone.infra.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException() {
        super("해당 영화가 존재하지 않습니다.");
    }
}

