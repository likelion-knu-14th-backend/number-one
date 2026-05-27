package hello.numberone.product.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("해당 user의 task가 존재하지 없습니다");
    }
}
