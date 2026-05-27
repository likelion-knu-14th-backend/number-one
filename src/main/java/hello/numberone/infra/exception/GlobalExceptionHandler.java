package hello.numberone.infra.exception;

import hello.numberone.domain.auth.exception.AlreadyEmailExistsException;
import hello.numberone.domain.auth.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("STUDENT_NOT_FOUND", e.getMessage()).toString());
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("MOVIE_NOT_FOUND", e.getMessage()).toString());
    }

    @ExceptionHandler(AlreadyEmailExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
            AlreadyEmailExistsException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("EMAIL_ALREADY_EXISTS", e.getMessage()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword(
            InvalidPasswordException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_PASSWORD", e.getMessage()));
    }
}
